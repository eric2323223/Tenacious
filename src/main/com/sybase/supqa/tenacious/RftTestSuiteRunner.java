package com.sybase.supqa.tenacious;

import java.util.ArrayList;
import java.util.List;

public class RftTestSuiteRunner {
	private List<String> allTests = new ArrayList<String>();
	private List<String> failedTests = new ArrayList<String>();
	
	public RftTestSuiteResult runScripts(){
		return null;
	}
	
	public void runTestSuite(List<String> tests){
		this.allTests = tests;
		for(String test:allTests){
			RftTestRunner runner = new RftTestRunner();
			RftTestResult result = runner.runTest(test);
			if(!result.isPass()){
				failedTests.add(test);
			}
		}
	}
	
	public int totalTestCount(){
		return allTests.size();
	}
	
}
