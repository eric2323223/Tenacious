package com.sybase.supqa.tenacious.policy;

import java.util.Date;

import com.sybase.supqa.tenacious.CleanupStatus;
import com.sybase.supqa.tenacious.RftTestSuiteRunner;

public class TimePeriodPolicy extends DefaultPolicy {

	@Override
	public CleanupStatus getCleanUpStatus(RftTestSuiteRunner runner) {
		long start = runner.getStartTime();
		long now = new Date().getTime();
		long difference = new Integer(getThreshold().get(PolicyType.TIME_PERIOD)).intValue()*1000;
		if(now-start > difference){
			System.out.println("TimePeriodPolicy");
			runner.resetStartTime();
			return CleanupStatus.ULTIMATE_CLEANUP;
		}else{
			return super.getCleanUpStatus(runner);
		}
	}

}
