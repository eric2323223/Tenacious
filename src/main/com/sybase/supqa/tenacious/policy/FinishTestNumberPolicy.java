package com.sybase.supqa.tenacious.policy;
import com.sybase.supqa.tenacious.CleanupStatus;
import com.sybase.supqa.tenacious.RftTestSuiteRunner;

public class FinishTestNumberPolicy extends DefaultPolicy {

	@Override
	public CleanupStatus getCleanUpStatus(RftTestSuiteRunner runner) {
		int count = new Integer(getThreshold().get(PolicyType.FINISHED_TEST_NUMBER)).intValue();
		if(runner.getFinishedTestsCount() >= count){
			runner.resetFinishedTest();
			System.out.println("FinishTestNumerPolicy");
			return CleanupStatus.ULTIMATE_CLEANUP;
		}
		return super.getCleanUpStatus(runner);
	}

}
