package com.sybase.supqa.tenacious;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.sybase.supqa.tenacious.PolicyFactoryTest;
import com.sybase.supqa.tenacious.util.EnvironementVariableUtilTest;

@RunWith(Suite.class)
@SuiteClasses({
	PolicyFactoryTest.class, 
	PolicyConfigTest.class,
	EnvironementVariableUtilTest.class,
	CleanupHandlerFactoryTest.class,
	RftHtmlLogParserTest.class,
	RftTestResultTest.class,
	RftTestScriptTest.class,
	TestQueueTest.class,
	TenaciousTest.class,
	})
public class AllTests {

}
