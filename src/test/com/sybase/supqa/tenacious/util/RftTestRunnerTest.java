package com.sybase.supqa.tenacious.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import com.sybase.supqa.tenacious.RftTestRunner;

public class RftTestRunnerTest {

	private RftTestRunner runner;
	
	@Before
	public void setup(){
		runner = new RftTestRunner();
	}

	@Test
	public void shouldReturnResult() {
		assertNotNull(runner.runTest(null));
	}

}
