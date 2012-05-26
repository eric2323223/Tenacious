package com.sybase.supqa.tenacious;


public class RftTestScript {
	private final String name;
	private RftTestResult result;
	
	public String getName() {
		return name;
	}

	public RftTestResult getResult() {
		return result;
	}

	public RftTestScript(String name){
		this.name = name;
	}

}
