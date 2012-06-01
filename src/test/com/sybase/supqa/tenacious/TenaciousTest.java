package com.sybase.supqa.tenacious;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.sybase.supqa.tenacious.policy.IExecutionPolicy;
import com.sybase.supqa.tenacious.policy.PolicyConfig;
import com.sybase.supqa.tenacious.policy.PolicyFactory;


public class TenaciousTest {
	private Tenacious tenacious;
	private TenaciousConfig config;
	private TestQueue testQueue;
	private IExecutionPolicy policy;
	
	@Before public void setup(){
		config = new TestConfig();
		tenacious = new Tenacious(config);
		TenaciousConfig config = new TenaciousConfig();
		policy = PolicyFactory.getPolicy(new PolicyConfig(config.getTenaciousPolicyConfigFile()));
	}
	
	@Test public void shouldInstall(){
		tenacious.install();
		File file1 = new File(config.getTenaciousRootPath()+File.separator+"teancious.bat");
		assertEquals(true, file1.exists());
		File file2 = new File(config.getWindowsStartupFolder()+File.separator+"tenacious.bat");
		assertEquals(true, file2.exists());
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
	
	@Test public void shouldNotTestWhenNoToDoTests(){
		testQueue = new TestQueue(config.getTenaciousRootPath()+File.separator
				+"src"+File.separator+"test"+File.separator+"fixture"+File.separator
				+"TestQueue_None");
		tenacious.runTests(testQueue, policy);
		assertEquals(0, testQueue.getAllTests().size());
	}
	
	@Test public void shouldTestAllScripts(){
		testQueue = new TestQueue(config.getTenaciousRootPath()+File.separator
				+"src"+File.separator+"test"+File.separator+"fixture"+File.separator
				+"TestQueue");
		tenacious.runTests(testQueue, policy);
		assertEquals(0, testQueue.getTodoTests().size());
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
