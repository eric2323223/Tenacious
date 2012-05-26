package com.sybase.supqa.tenacious.policy;

import java.util.Date;

import com.sybase.supqa.tenacious.CleanUpStatus;
import com.sybase.supqa.tenacious.RftTestSuiteRunner;

public class TimePeriodPolicy extends DefaultPolicy {

	@Override
	public CleanUpStatus getCleanUpStatus(RftTestSuiteRunner runner) {
		long start = runner.getStartTime();
		long now = new Date().getTime();
		long difference = new Integer(getThreshold().get(PolicyType.TIME_PERIOD)).intValue()*1000;
		if(now-start > difference){
			return CleanUpStatus.ULTIMATE_CLEANUP;
		}else{
			return CleanUpStatus.NO_NEED_CLEANUP;
		}
	}

}
