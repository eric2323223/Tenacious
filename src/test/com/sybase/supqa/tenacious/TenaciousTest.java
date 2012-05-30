package com.sybase.supqa.tenacious;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;


public class TenaciousTest {
	private Tenacious tenacious;
	private TenaciousConfig config;
	
	@Before public void setup(){
		config = new TestConfig();
		tenacious = new Tenacious(config);
	}
	
	@Test public void shouldInstallInFirstTime(){
		
	}
	
	@Test public void shouldrunTestsInTestQueueUntilResultNotChange(){
//		tenacious.runTests();
	}
	
	@Test public void shouldGetJavaPath(){
		String code = tenacious.generateTenaciousStartBatchCode();
		assertEquals("\"C:\\Program Files\\IBM\\SDP\\jdk\\jre\\bin\\java.exe\" -cp " +
				"\"C:\\Documents and Settings\\test\\Tenacious\\bin\";" +
				"\"C:\\Documents and Settings\\test\\Tenacious\\lib\\*\" " +
				"com.sybase.supqa.tenacious.Tenacious", code);
	}
	
//	@After public void cleanup(){
//		deleteTestQueueFile();
//	}
	
	private void deleteTestQueueFile() {
		File file = new File(config.getTenaciousTestQueueFile());
		file.delete();
	}

	public class TestConfig extends TenaciousConfig{
		public String getTenaciousPropertiesFile() {
			return getTenaciousRootPath()+File.separator+"tenacious.properties";
		}
		
		public String getTenaciousPolicyConfigFile() {
			return getTenaciousRootPath()+File.separator+"policy.xml";
		}
		
		public String getTenaciousTestQueueFile() {
			return getTenaciousRootPath()+File.separator+"test"+File.separator+"fixture"+File.separator+"TestResults"+File.separator+"TestQueue.txt";
		}
	}

}