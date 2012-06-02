package com.sybase.supqa.tenacious.policy;

import com.sybase.supqa.tenacious.CleanUpStatus;
import com.sybase.supqa.tenacious.RftTestSuiteRunner;

public class FinishTestNumberPolicy extends DefaultPolicy {

	@Override
	public CleanUpStatus getCleanUpStatus(RftTestSuiteRunner runner) {
		int count = new Integer(getThreshold().get(PolicyType.FINISHED_TEST_NUMBER)).intValue();
		System.out.println(runner.getFinishedTestsCount());
		if(runner.getFinishedTestsCount() >= count){
//			runner.resetFinishedTest();
			return CleanUpStatus.ULTIMATE_CLEANUP;
		}
		return CleanUpStatus.NO_NEED_CLEANUP;
	}

}
