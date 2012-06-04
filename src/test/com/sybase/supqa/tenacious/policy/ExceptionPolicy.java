package com.sybase.supqa.tenacious.policy;

import com.sybase.supqa.tenacious.CleanupStatus;
import com.sybase.supqa.tenacious.RftTestResult;
import com.sybase.supqa.tenacious.RftTestSuiteRunner;

public class ExceptionPolicy extends DefaultPolicy {

	@Override
	public CleanupStatus getCleanUpStatus(RftTestSuiteRunner runner) {
		RftTestResult result = runner.getCurrentTestResult();
		if(result.getException().contains(getThreshold().get("exceptionName"))){
			return CleanupStatus.ADVANCED_CLEANUP;
		}
		return CleanupStatus.NO_NEED_CLEANUP;
	}

}
