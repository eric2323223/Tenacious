package com.sybase.supqa.tenacious.policy;

import com.sybase.supqa.tenacious.CleanUpStatus;
import com.sybase.supqa.tenacious.RftTestSuiteRunner;

public class FinishTestNumberPolicy extends DefaultPolicy {

	@Override
	public CleanUpStatus getCleanUpStatus(RftTestSuiteRunner runner) {
		int count = new Integer(getThreshold().get(PolicyType.FINISHED_TEST_NUMBER)).intValue();
		if(runner.getFinishedTestsCount() >= count){
			return CleanUpStatus.ULTIMATE_CLEANUP;
		}
		return super.getCleanUpStatus(runner);
	}

}
