package com.sybase.supqa.tenacious;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.sybase.supqa.tenacious.policy.PolicyFactoryTest;
import com.sybase.supqa.tenacious.util.EnvironementVariableUtilTest;

@RunWith(Suite.class)
@SuiteClasses({PolicyConfigTest.class, 
	PolicyFactoryTest.class, 
	EnvironementVariableUtilTest.class,
	CleanupHandlerFactoryTest.class,
	RftHtmlLogParserTest.class,
	RftTestResultTest.class,
	RftTestScriptTest.class
	})
public class AllTests {

}
