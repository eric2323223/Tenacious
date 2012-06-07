package com.sybase.supqa.tenacious;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TenaciousConfigTest {
	private TenaciousConfig config;

	@Before
	public void setUp() throws Exception {
		config = new TenaciousConfig();
	}

	@Test
	public void shouldGetScriptInFolder(){
		assertEquals(92, config.getTestScriptsInFolder("testscript\\Workflow\\Actions").size());
	}
}
