package com.sybase.supqa.tenacious.policy;

import com.sybase.supqa.tenacious.CleanUpStatus;
import com.sybase.supqa.tenacious.RftTestSuiteRunner;

public class ResourceUsagePolicy extends DefaultPolicy{

	@Override
	public CleanUpStatus getCleanUpStatus(RftTestSuiteRunner runner) {
		return super.getCleanUpStatus(runner);
	}


}
