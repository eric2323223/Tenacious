package com.sybase.supqa.tenacious;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.sybase.supqa.tenacious.policy.IExecutionPolicy;


public class RftTestSuiteRunnerTest {
	private RftTestSuiteRunner runner;

	@Before public void setup(){
		runner = new RftTestSuiteRunner();
	}
	
	@Test public void shouldApplyPolicy(){
//		IExecutionPolicy mockPolicy = mock(IExecutionPolicy.class);
//		when(mockPolicy.getCleanUpStatus(runner)).thenReturn();
//		
//		ICleanupHandler handler = mock(ICleanupHandler.class);
//		when()
//		runner.runTestSuite(mockPolicy, queue, handler);
//		verify(handler, times(5)).handle();
	}

}
