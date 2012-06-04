package com.sybase.supqa.tenacious;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.sybase.supqa.tenacious.policy.IExecutionPolicy;
import com.sybase.supqa.tenacious.util.FileUtil;


public class TestQueueTest {
	
	private TestQueue testQueue;
	private TenaciousConfig config = new TenaciousConfig();

	@Before public void setup() throws IOException{
		String from = config.getTestFixureFolder()+File.separator+"TestQueue";
		String to = config.getTenaciousRootPath()+File.separator+"testResults"+File.separator+"TestQueue";
		FileUtil.copyFile(from, to);
		testQueue = new TestQueue(to);
	}
	
	@Test public void shouldGetAllTests(){
		assertEquals(8, testQueue.getAllTests().size());
	}
	
	@Test public void shouldGetTodoTest(){
		List<String> todoTests = testQueue.getTodoTests();
		assertEquals(5, todoTests.size());
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
		assertEquals(4, testQueue.getTodoTests().size());
	}

}
