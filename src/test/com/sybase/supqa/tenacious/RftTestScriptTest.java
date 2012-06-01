package com.sybase.supqa.tenacious;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RftTestScriptTest {
	private RftTestScript script;
	
	@Before public void setup(){
		script = new RftTestScript("testscript.workflow.screen.script1");
	}

	@Test
	public void shouldGetLogFileName() {
		assertEquals("C:\\Documents and Settings\\eric\\IBM\\rationalsdp\\workspace\\UEP_ET_logs\\testscript\\workflow\\screen\\script1\\rational_ft_logframe.html", script.getLogFileName());
	}
	
	@Test 
	public void shouldGenerateRftCliCommand(){
		assertEquals("\"C:\\Program Files\\IBM\\SDP\\jdk\\jre\\bin\\java.exe\" " +
				"-classpath \"C:\\Documents and Settings\\eric\\IBM\\rationalsdp\\workspace\\UEP_ET\";\"C:\\Program Files\\IBM\\SDP\\FunctionalTester\\bin\\rational_ft.jar\";\"C:\\Documents and Settings\\eric\\IBM\\rationalsdp\\workspace\\UEP_ET\\lib\\*\" " +
				"com.rational.test.ft.rational_ft " +
				"-datastore \"C:\\Documents and Settings\\eric\\IBM\\rationalsdp\\workspace\\UEP_ET\" " +
				"-playback testscript.workflow.screen.script1", script.buildRftPlaybackCommandString());
	}
	
//	@Test 
	public void shouldRunTest(){
		script = new RftTestScript("Test");
		RftTestResult result = script.run();
		assertEquals(true, result.isPass());
	}
	
}
