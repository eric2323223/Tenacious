package com.sybase.supqa.tenacious.policy;

import java.util.Hashtable;

import com.sybase.supqa.tenacious.CleanUpStatus;
import com.sybase.supqa.tenacious.RftTestSuiteRunner;

public class DefaultPolicy implements IExecutionPolicy {
	private Hashtable<String, String> threshold = new Hashtable<String, String>();

	@Override
	public CleanUpStatus getCleanUpStatus(RftTestSuiteRunner runner) {
		return CleanUpStatus.NO_NEED_CLEANUP;
	}

	@Override
	public Hashtable<String, String> getThreshold() {
		return threshold;
	}

	@Override
	public void addThreshold(String key, String value) {
		threshold.put(key, value);
	}

}
