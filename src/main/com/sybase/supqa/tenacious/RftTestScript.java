package com.sybase.supqa.tenacious;


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
		this.logFileName = new TenaciousConfig().getSuptafRootPath();
	}

	public void run() {
		
		
	}

}
