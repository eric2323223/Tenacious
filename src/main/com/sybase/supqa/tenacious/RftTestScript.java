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

	public RftTestResult run() {
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

	String buildRftPlaybackCommandString() {
		TenaciousConfig config = new TenaciousConfig();
		String rftFtJar = StringUtil.quote(config.getRftFtJar());
		String suptafRoot = StringUtil.quote(config.getSuptafRootPath());
		String suptafLib = StringUtil.quote(config.getSuptafRootPath()+"\\lib\\*");
		String mainClass = "com.rational.test.ft.rational_ft";
		String jre = Tenacious.getJavaPath();
		
		return jre+" -classpath "+suptafRoot+File.pathSeparator+rftFtJar+File.pathSeparator+suptafLib+ 
			" "+mainClass+" -datastore "+suptafRoot+" -playback "+this.name;
	}
	
	public static void main(String[] args) throws IOException{
		Runtime.getRuntime().exec("\"C:\\Program Files\\IBM\\SDP\\jdk\\jre\\bin\\java.exe\" " +
				"-classpath \"C:\\Documents and Settings\\test\\IBM\\rationalsdp\\workspace\\UEP_ET\";\"C:\\Program Files\\IBM\\SDP\\FunctionalTester\\bin\\rational_ft.jar\";\"C:\\Documents and Settings\\test\\IBM\\rationalsdp\\workspace\\UEP_ET_log\\lib\\*\" " +
				"com.rational.test.ft.rational_ft " +
				"-datastore \"C:\\Documents and Settings\\test\\IBM\\rationalsdp\\workspace\\UEP_ET\" " +
				"-rt.bring_up_logviewer false "+
				"-playback Test");
		System.out.println("done");
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
