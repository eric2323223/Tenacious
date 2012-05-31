package com.sybase.supqa.tenacious;

import java.io.File;
import java.io.IOException;

import com.sybase.supqa.tenacious.util.Cmd;
import com.sybase.supqa.tenacious.util.StringUtil;

public class RftTestScript {
	private final String name;
	private final String logFileName;
	private RftTestResult result;
	
	public String getName() {
		return name;
	}

	public RftTestResult getResult() {
		return result;
	}

	public RftTestScript(String name){
		this.name = name;
		String root = new TenaciousConfig().getSuptafLogRootPath();
		String path = name.replace(".", "\\");
		logFileName = root+File.separator+path+File.separator+"rational_ft_logframe.html";
	}
	
	public String getLogFileName(){
		return logFileName;
	}

	public RftTestResult run() {
		Cmd.execute(buildRftPlaybackCommandString());
		result = new RftTestResult(logFileName);
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
				"-playback Test");
		System.out.println("done");
	}
	
}
