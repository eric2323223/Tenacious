package com.sybase.supqa.tenacious.policy;

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
	public void setThreshold(String value);
	public String getThreshold();
}
