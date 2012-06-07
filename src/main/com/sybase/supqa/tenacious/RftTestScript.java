package com.sybase.supqa.tenacious;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.sybase.supqa.tenacious.util.Cmd;
import com.sybase.supqa.tenacious.util.StringUtil;

public class RftTestScript {
	protected final String name;
	private final String logFileName;
	protected RftTestResult result;
	
	public String getName() {
		return name;
	}

	public RftTestResult getResult() {
		return result;
	}

	public RftTestScript(String name){
		this.name = name;
		String root = new TenaciousConfig().getSuptafLogRootPath();
//		String path = name.replace(".", "\\");
		logFileName = root+File.separator+name+File.separator+"rational_ft_logframe.html";
	}
	
	public String getLogFileName(){
		return logFileName;
	}

	public RftTestResult run2() {
//		System.out.println("running test case "+name);
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		TenaciousConfig config = new TenaciousConfig();
//		result = new RftTestResult(config.getTestFixureFolder()+File.separator+"logWithException.html");
//		this.setResult(result);
//		return result;
		
//		Cmd.execute(buildRftPlaybackCommandString());
		try {
			Cmd.executeCommandLine(buildRftPlaybackCommandString(), 600);
			result = new RftTestResult(logFileName);
		} catch (TimeoutException e) {
			e.printStackTrace();
			result = new RftTestResult();
			result.setException(e.getClass().getName());
		}
		this.setResult(result);
		return result;
	}
	
	public RftTestResult run(int timeout) {
		try {
			Cmd.executeCommandLine(buildRftPlaybackCommandString(), timeout);
			result = new RftTestResult(logFileName);
		} catch (TimeoutException e) {
			e.printStackTrace();
			result = new RftTestResult();
			result.setException(e.getClass().getName());
		}
		this.setResult(result);
		return result;
	}
	
	public RftTestResult run() {
		TenaciousConfig config = new TenaciousConfig();
		try {
			System.out.println("run script "+name);
			Cmd.executeCommandLine(buildRftPlaybackCommandString(), config.getTimeoutOfTestExecution()*1000);
			result = new RftTestResult(logFileName);
		} catch (TimeoutException e) {
//			e.printStackTrace();
			result = new RftTestResult();
			result.setException(e.getClass().getName());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.setResult(result);
		return result;
	}
	
	public RftTestResult run3(){
		Cmd.executeWithoutWait(buildRftPlaybackCommandString());
		while(!isTestComplete()){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		closeIE();
		result = new RftTestResult(logFileName);
		return result;
	}
	
	boolean isTestComplete(){
		return Cmd.getOutput("tasklist | findstr \"IEXPLORE\"").trim().length()>0;
	}
	
	void closeIE(){
		Cmd.execute("cmd /c taskkill /F /IM IEXPLORE.EXE /T");
	}

	String buildRftPlaybackCommandString() {
		TenaciousConfig config = new TenaciousConfig();
		String rftFtJar = StringUtil.quote(config.getRftFtJar());
		String suptafRoot = StringUtil.quote(config.getSuptafRootPath());
		String suptafLib = StringUtil.quote(config.getSuptafRootPath()+"\\lib\\*");
		String ibmSharedLib = StringUtil.quote(config.getIbmSharedLibPath()+"\\*");
		String mainClass = "com.rational.test.ft.rational_ft";
		String jre = Tenacious.getJavaPath();
		
		return jre+" -classpath "+suptafRoot+File.pathSeparator+rftFtJar+
			File.pathSeparator+suptafLib+ 
			" "+mainClass+" -datastore "+suptafRoot+" -playback "+this.name;
	}
	
	public static void main(String[] args) throws IOException{
		RftTestScript script = new RftTestScript("testscript.Workflow.Keys.Action_ParameterMapping");
		script.run3();
		System.out.println(script.getResult().getException());
	}
	
	public String toString(){
		String status="";
		String detail= "";
		RftTestResult result = getResult();
		if(result.isPass()){
			status = "pass";
		}else{
			if(result.getException()!=null){
				status = "exception";
				detail = result.getException();
			}else{
				status = "fail";
				detail = result.getFailedVerificationPoints().toString();
			}
		}
		return this.name+"\t"+status+"\t"+detail;
	}

	public void setResult(RftTestResult result) {
		this.result = result;
	}
	
}
