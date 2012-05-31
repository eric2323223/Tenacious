package com.sybase.supqa.tenacious;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sybase.supqa.tenacious.policy.PolicyConfig;
import com.sybase.supqa.tenacious.policy.PolicyFactory;
import com.sybase.supqa.tenacious.util.PropertiesFileHelper;
import com.sybase.supqa.tenacious.util.StringUtil;

public class Tenacious {
	private TenaciousConfig tenaciousConfig;
	
	public Tenacious(TenaciousConfig c){
		this.tenaciousConfig = c;
	}

	public static void main(String[] args){
		Tenacious tenacious = new Tenacious(new TenaciousConfig());
		if(tenacious.ifTenaciousInstalled()){
			tenacious.install();
		}
		tenacious.runTests(tenacious.loadTestQueue());
	}

	void runTests(List<RftTestScript> tests) {
		if(tests.size()>0){
			RftTestSuiteRunner runner = new RftTestSuiteRunner();
			PropertiesFileHelper config = new PropertiesFileHelper(tenaciousConfig.getTenaciousPolicyConfigFile());
			runner.runTestSuite(tests, PolicyFactory.getPolicy(config));
			List<RftTestScript> failedTests = loadTestQueue();
			if(tests.size()==failedTests.size()){
				cleanTestQueue();
				return;
			}else{
				ICleanupHandler handler = CleanupHandlerFactory.getHandler(new PolicyConfig(tenaciousConfig.getTenaciousPolicyConfigFile()));
				handler.ultimateCleanup();
			}
		}
	}

	private void cleanTestQueue() {
		File file = new File(tenaciousConfig.getTenaciousTestQueueFile());
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write("");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private List<RftTestScript> loadTestQueue() {
		List<RftTestScript> tests = new ArrayList<RftTestScript>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(tenaciousConfig.getTenaciousTestQueueFile()));
			String line;
			while((line=reader.readLine())!=null){
				tests.add(new RftTestScript(line));
			}
			return tests;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean ifTenaciousInstalled(){
		File configFile = getTenaciousBatchFile();
		if(configFile.exists()){
			return true;
		}else{
			return false;
		}
	}
	
	private File getTenaciousPropertiesFile(){
		PropertiesFileHelper config = new PropertiesFileHelper(tenaciousConfig.getTenaciousPropertiesFile());
		String startFolder = config.getProperty("START_FOLDER");
		return new File(startFolder+File.separator+"tenacious.properties");
	}
	
	private void install(){
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
		PropertiesFileHelper config = new PropertiesFileHelper(tenaciousConfig.getTenaciousPropertiesFile());
		String startFolder = config.getProperty("START_FOLDER");
		return new File(startFolder+File.separator+"tenacious.properties");
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
