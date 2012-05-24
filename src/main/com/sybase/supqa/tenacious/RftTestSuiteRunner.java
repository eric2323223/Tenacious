package com.sybase.supqa.tenacious;

import java.util.ArrayList;
import java.util.List;

import com.sybase.supqa.tenacious.policy.IExecutionPolicy;

public class RftTestSuiteRunner {
	private List<String> allTests = new ArrayList<String>();
	private List<String> failedTests = new ArrayList<String>();
	
	public RftTestSuiteResult runScripts(){
		return null;
	}
	
	public List<String> runTestSuite(List<String> tests, IExecutionPolicy policy){
		this.allTests = tests;
		for(String test:allTests){
			RftTestRunner runner = new RftTestRunner();
			policy.beforeRunTest();
			RftTestResult result = runner.runTest(test);
			policy.afterRunTest();
			if(!result.isPass()){
				failedTests.add(test);
			}
		}
		return failedTests;
	}
	
	public int totalTestCount(){
		return allTests.size();
	}
	
}
