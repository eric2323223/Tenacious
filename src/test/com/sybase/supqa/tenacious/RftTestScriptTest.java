package com.sybase.supqa.tenacious;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RftTestScriptTest {
	private RftTestScript script;
	
	@Before public void setup(){
		script = new RftTestScript("testscript.workflow.screen.script1");
	}

	@Test
	public void shouldGetLogFileName() {
		assertEquals("C:Documents and Settings	estIBMationalsdpworkspaceUEP_ET_log\testscript.workflow.screen.script1", script.getLogFileName());
	}

}
