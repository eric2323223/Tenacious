package com.sybase.supqa.tenacious;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.sybase.supqa.tenacious.policy.DefaultPolicy;
import com.sybase.supqa.tenacious.policy.ExceptionPolicy;
import com.sybase.supqa.tenacious.policy.FinishTestNumberPolicy;
import com.sybase.supqa.tenacious.policy.IExecutionPolicy;
import com.sybase.supqa.tenacious.policy.PolicyType;
import com.sybase.supqa.tenacious.policy.ResourceUsagePolicy;
import com.sybase.supqa.tenacious.policy.TimePeriodPolicy;
import com.sybase.supqa.tenacious.util.FileUtil;


public class RftTestSuiteRunnerTest {
	private RftTestSuiteRunner runner;
	private TestQueue queue;
	private TenaciousConfig config = new TenaciousConfig();
	private ICleanupHandler handler;
	private IExecutionPolicy policy;

	@Before public void setup() throws IOException{
		String sourceFile = config.getTestFixureFolder()+File.separator+"TestQueue";
		String destFile = config.getDefaultTestQueue();
		FileUtil.copyFile(sourceFile, destFile);
		runner = new RftTestSuiteRunner();
		queue = new TestQueue(config.getDefaultTestQueue());
		handler = new CleanupHandlerForTest();
	}
	
//	@Test 
	public void shouldCompleteAllTests(){
		policy = new DefaultPolicy();
		assertEquals(12, queue.getTodoTests().size());
		runner.runTestSuite(policy, queue, handler);
		assertEquals(0, queue.getTodoTests().size());
	}
	
//	@Test
	public void shouldApplyTimePeriodPolicy(){
		policy = new TimePeriodPolicy();
		policy.addThreshold(PolicyType.TIME_PERIOD, "2");
		runner.runTestSuite(policy, queue, handler);
		assertEquals(0, queue.getTodoTests().size());
	}
	
//	@Test 
	public void shouldApplyFinishTestNumberPolicy(){
		CleanupHandlerForTest mockHandler = new CleanupHandlerForTest();
		policy = new FinishTestNumberPolicy();
		policy.addThreshold(PolicyType.FINISHED_TEST_NUMBER, "3");
		runner.runTestSuite(policy, queue, mockHandler);
//		verify(mockHandler, times(1)).ultimateCleanup();
	}
	
//	@Test
	public void shouldApplyResourceUsagePolicy(){
		CleanupHandlerForTest mockHandler = new CleanupHandlerForTest();
		policy = new ResourceUsagePolicy();
		policy.addThreshold(PolicyType.RESOURCE_CPU, "0.8000");
		runner.runTestSuite(policy, queue, mockHandler);
	}
	
	@Test
	public void shouldApplyExceptionPolicy() throws IOException{
		CleanupHandlerForTest handler = new CleanupHandlerForTest();
		policy = new ExceptionPolicy();
		policy.addThreshold("exceptionName", "java.lang.ClassNotFoundException");
		runner.runTestSuite(policy, queue, handler);
	}
	
	class MockScript extends RftTestScript{

		public MockScript(String name) {
			super(name);
		}
		
		public RftTestResult run(){
			System.out.println("running test case "+name);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			TenaciousConfig config = new TenaciousConfig();
			result = new RftTestResult(config.getTestFixureFolder()+File.separator+"logWithException.html");
			this.setResult(result);
			return result;
		}
		
	}
	
}
