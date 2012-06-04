package com.sybase.supqa.tenacious;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.sybase.supqa.tenacious.policy.IExecutionPolicy;
import com.sybase.supqa.tenacious.policy.PolicyConfig;
import com.sybase.supqa.tenacious.policy.PolicyFactory;
import com.sybase.supqa.tenacious.util.StringUtil;

public class Tenacious {
	private TenaciousConfig tenaciousConfig;
	
	public Tenacious(TenaciousConfig c){
		this.tenaciousConfig = c;
	}

	public static void main(String[] args){
		TenaciousConfig config = new TenaciousConfig();
		Tenacious tenacious = new Tenacious(config);
//		if(tenacious.ifTenaciousInstalled()){
//			tenacious.generateStartupBatchFile();
//		}
		tenacious.install();
		TestQueue testQueue = new TestQueue(config.getTenaciousTestQueueFile());
		PolicyConfig policyConfig = new PolicyConfig(config.getTenaciousPolicyConfigFile());
		tenacious.runTests(testQueue, PolicyFactory.getPolicy(policyConfig));
	}

	void runTests(TestQueue queue, IExecutionPolicy policy) {
		ICleanupHandler handler = CleanupHandlerFactory.getHandler(new PolicyConfig(new TenaciousConfig().getTenaciousPolicyConfigFile()));
		List<String> tests = queue.getTodoTests();
		if(tests.size()>0){
			RftTestSuiteRunner runner = new RftTestSuiteRunner();
			runner.runTestSuite(policy, queue, handler);
			List<String> todoTests = queue.getTodoTests();
			if(tests.size()==todoTests.size()){
				queue.clear();
				return;
			}else{
				handler.ultimateCleanup();
			}
		}
	}
	
	private boolean ifTenaciousInstalled(){
		File configFile = getTenaciousStartupBatchFile();
		if(configFile.exists()){
			return true;
		}else{
			return false;
		}
	}
	
	void install(){
		generateLocalBatchFile();
		generateStartupBatchFile();
	}
	
	void generateStartupBatchFile(){
		File tenaciousBatchFile = getTenaciousStartupBatchFile();
		FileWriter writer = null;
		try {
			writer = new FileWriter(tenaciousBatchFile);
			writer.write(startSupWorkspaceBatchCode());
			writer.write("\n");
			writer.write(generateTenaciousStartBatchCode());
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String startSupWorkspaceBatchCode() {
		return "start /B C:\\Sybase\\UnwiredPlatform\\Eclipse\\UnwiredWorkSpace.bat\n" +
				"ping -n 180 127.0.0.1 >null\n" +generateTenaciousStartBatchCode();
	}

	void generateLocalBatchFile(){
		File tenaciousBatchFile = getTenaciousBatchFile();
		FileWriter writer = null;
		try {
			writer = new FileWriter(tenaciousBatchFile);
			writer.write(generateTenaciousStartBatchCode());
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private File getTenaciousBatchFile() {
		return new File(tenaciousConfig.getTenaciousRootPath()+File.separator+"tenacious.bat");
	}

	private File getTenaciousStartupBatchFile() {
		String startFolder = tenaciousConfig.getWindowsStartupFolder();
		return new File(startFolder+File.separator+"tenacious.bat");
	}

	String generateTenaciousStartBatchCode() {
		String javaPath = getJavaPath();
		String classPath = "-cp "+StringUtil.quote(tenaciousConfig.getTenaciousRootPath()+File.separator+"bin")+
			File.pathSeparator+StringUtil.quote(tenaciousConfig.getTenaciousRootPath()+File.separator+"lib\\*");
		String mainClass = "com.sybase.supqa.tenacious.Tenacious";
		return javaPath+ " "+ classPath + " "+mainClass;
	}

	public static String getJavaPath() {
		String path = System.getProperty("sun.boot.library.path");
		return StringUtil.quote(path+File.separator+"java.exe");
	}

}
