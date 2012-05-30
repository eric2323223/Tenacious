package com.sybase.supqa.tenacious;

import java.io.File;


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
		String path = name.replace("\\.", File.separator);
		logFileName = root+File.separator+path;
	}
	
	public String getLogFileName(){
		return logFileName;
	}

	public void run() {
		
		
	}

}
