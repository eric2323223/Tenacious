package com.sybase.supqa.tenacious.util;

import static org.junit.Assert.*;

import org.junit.Test;


public class EnvironementVariableUtilTest {
	
	@Test
	public void shouldReadJavaHome(){
		assertNotNull(EnvironmentVariableUtil.getVariable("JAVA_HOME"));
	}

}
