package com.sybase.supqa.tenacious.policy;

import java.util.Hashtable;

import com.sybase.supqa.tenacious.CleanUpStatus;
import com.sybase.supqa.tenacious.RftTestSuiteRunner;

public interface IExecutionPolicy {

//	public void beforeRunTest();
//	public void afterRunTest();
//	public void basicCleanUp();
//	public void ultimateCleanUp();
//	public void onTestFail();
//	public boolean shouldCleanUp();
	public CleanUpStatus getCleanUpStatus(RftTestSuiteRunner runner);
	public void addThreshold(String key, String value);
	public Hashtable getThreshold();
}
