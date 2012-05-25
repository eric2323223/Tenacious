package com.sybase.supqa.tenacious.policy;

import com.sybase.supqa.tenacious.CleanUpStatus;
import com.sybase.supqa.tenacious.RftTestSuiteRunner;

public class DefaultPolicy implements IExecutionPolicy {
	private String threshold;

	@Override
	public CleanUpStatus getCleanUpStatus(RftTestSuiteRunner runner) {
		return CleanUpStatus.NO_NEED_CLEANUP;
	}

	@Override
	public String getThreshold() {
		return threshold;
	}

	@Override
	public void setThreshold(String value) {
		threshold = value;
	}

}
