package com.sybase.supqa.tenacious;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.sybase.supqa.tenacious.policy.IExecutionPolicy;


public class TestQueueTest {
	
	private TestQueue testQueue;
	private TenaciousConfig config = new TenaciousConfig();

	@Before public void setup(){
		testQueue = new TestQueue(config.getTenaciousRootPath()+File.separator
				+"src"+File.separator+"test"+File.separator+"fixture"+File.separator
				+"TestQueue");
	}
	
	@Test public void shouldGetAllTests(){
		assertEquals(5, testQueue.getAllTests().size());
	}
	
	@Test public void shouldGetTodoTest(){
		List<String> todoTests = testQueue.getTodoTests();
		assertEquals(2, todoTests.size());
	}
	
	@Test public void shouldGetDoneTest(){
		List<String> doneTests = testQueue.getDoneTests();
		assertEquals(3, doneTests.size());
	}
	
	@Test public void shouldUpdateTest(){
		RftTestScript script = new RftTestScript("Test4");
		RftTestResult result = new RftTestResult(config.getTestFixureFolder()+File.separator+"rational_ft_logframe.html");
		script.setResult(result);
		testQueue.updateTestStatus(script);
		assertEquals(1, testQueue.getTodoTests().size());
	}
	

}
