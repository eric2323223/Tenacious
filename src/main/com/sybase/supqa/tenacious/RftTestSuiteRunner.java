package com.sybase.supqa.tenacious;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sybase.supqa.tenacious.policy.DefaultPolicy;
import com.sybase.supqa.tenacious.policy.IExecutionPolicy;
import com.sybase.supqa.tenacious.policy.PolicyConfig;
import com.sybase.supqa.tenacious.policy.PolicyFactory;
import com.sybase.supqa.tenacious.policy.PolicyType;
import com.sybase.supqa.tenacious.policy.TimePeriodPolicy;

public class RftTestSuiteRunner {
	private List<RftTestScript> finishedTests = new ArrayList<RftTestScript>();
	private List<RftTestScript> allTests = new ArrayList<RftTestScript>();
	private List<RftTestScript> failedTests = new ArrayList<RftTestScript>();
	private RftTestResult currentTestResult;
	private final long start = new Date().getTime();
	
	public void runTestSuite(IExecutionPolicy policy, TestQueue queue, ICleanupHandler handler){
		while(queue.getTodoTests().size()>0){
			RftTestScript script = new RftTestScript(queue.getTodoTests().get(0));
			currentTestResult = script.run();
			finishedTests.add(script);
			queue.updateTestStatus(script);
			if(policy.getCleanUpStatus(this)!=CleanUpStatus.NO_NEED_CLEANUP){
				if(policy.getCleanUpStatus(this)==CleanUpStatus.BASIC_CLEANUP){
					handler.basicCleanup();
				}
				if(policy.getCleanUpStatus(this)==CleanUpStatus.ADVANCED_CLEANUP){
					handler.advancedCleanup();
				}
				if(policy.getCleanUpStatus(this)==CleanUpStatus.ULTIMATE_CLEANUP){
					handler.ultimateCleanup();
				}
			}
		}
	}
	
	public int totalTestCount(){
		return allTests.size();
	}
	
	public void resetFinishedTest(){
		finishedTests.clear();
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
	
	public static void main(String[] args){
		TenaciousConfig config = new TenaciousConfig();
		IExecutionPolicy policy = PolicyFactory.getPolicy(new PolicyConfig(config.getTestFixureFolder()+File.separator+"policy_TimePeriod.xml"));
		RftTestSuiteRunner runner = new RftTestSuiteRunner();
		TestQueue queue = new TestQueue(config.getDefaultTestQueue());
		CleanupHandlerForTest handler = new CleanupHandlerForTest();
		
//		runner.runTestSuite(policy, queue, handler);
		//TODO watch this
		policy = new DefaultPolicy();
		runner.runTestSuite(policy, queue, handler);
	}
	
}
