package com.sybase.supqa.tenacious;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sybase.supqa.tenacious.policy.IExecutionPolicy;
import com.sybase.supqa.tenacious.policy.PolicyConfig;

public class RftTestSuiteRunner {
	private List<RftTestScript> finishedTests = new ArrayList<RftTestScript>();
	private List<RftTestScript> allTests = new ArrayList<RftTestScript>();
	private List<RftTestScript> failedTests = new ArrayList<RftTestScript>();
	private RftTestResult currentTestResult;
	private final long start = new Date().getTime();
	
//	public List<RftTestScript> runTestSuite(List<String> tests, IExecutionPolicy policy){
//		this.allTests = tests;
//		for(RftTestScript test:allTests){
//			currentTestResult = test.run();
//			updateTestQueueFile(test);
//			finishedTests.add(test);
//			if(!currentTestResult.isPass()){
//				failedTests.add(test);
//			}
//			ICleanupHandler handler = CleanupHandlerFactory.getHandler(new PolicyConfig(""));
//			if(policy.getCleanUpStatus(this)==CleanUpStatus.BASIC_CLEANUP){
//				handler.basicCleanup();
//			}
//			if(policy.getCleanUpStatus(this)==CleanUpStatus.ADVANCED_CLEANUP){
//				handler.advancedCleanup();
//			}
//			if(policy.getCleanUpStatus(this)==CleanUpStatus.ULTIMATE_CLEANUP){
//				handler.ultimateCleanup();
//			}
//		}
//		return failedTests;
//	}

	public void runTestSuite(IExecutionPolicy policy, TestQueue queue, ICleanupHandler handler){
		List<String> tests = queue.getTodoTests();
		for(String test:tests){
			RftTestScript script = new RftTestScript(test);
			currentTestResult = script.run();
			script.setResult(currentTestResult);
//			ICleanupHandler handler = CleanupHandlerFactory.getHandler(new PolicyConfig(new TenaciousConfig().getTenaciousPolicyConfigFile()));
			handler.handle(policy, this);
//			if(policy.getCleanUpStatus(this)==CleanUpStatus.BASIC_CLEANUP){
//				handler.basicCleanup();
//			}
//			if(policy.getCleanUpStatus(this)==CleanUpStatus.ADVANCED_CLEANUP){
//				handler.advancedCleanup();
//			}
//			if(policy.getCleanUpStatus(this)==CleanUpStatus.ULTIMATE_CLEANUP){
//				handler.ultimateCleanup();
//			}
		}
	}
	
	public int totalTestCount(){
		return allTests.size();
	}
	
	public RftTestResult getCurrentTestResult(){
		return currentTestResult;
	}

	public long getStartTime() {
		return this.start;
	}

	public int getFinishedTestsCount() {
		return finishedTests.size();
	}
	
}
