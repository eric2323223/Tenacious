package com.sybase.supqa.tenacious.util;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sybase.supqa.tenacious.RftTestSuiteRunner;


public class RftTestSuiteRunnerTest {

	
	private RftTestSuiteRunner runner;
	
	@Before public void setup(){
		runner = new RftTestSuiteRunner();
	}

	@Test public void shouldRemovePassedTestsFromAllTests(){
//		List<String> failedTests = runner.runTestSuite(testSuite());
//		assert
	}

	private List<String> testSuite() {
		return null;
	}
}
