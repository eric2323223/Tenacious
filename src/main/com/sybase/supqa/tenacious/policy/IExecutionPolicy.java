package com.sybase.supqa.tenacious.policy;

import java.util.Hashtable;

import com.sybase.supqa.tenacious.CleanupStatus;
import com.sybase.supqa.tenacious.RftTestSuiteRunner;

public interface IExecutionPolicy {

	public CleanupStatus getCleanUpStatus(RftTestSuiteRunner runner);
	public void addThreshold(String key, String value);
	public Hashtable getThreshold();
}
