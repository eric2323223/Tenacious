package com.sybase.supqa.tenacious.policy;

import com.sybase.supqa.tenacious.util.ConfigManager;

public class PolicyFactory {
	
	public static IExecutionPolicy getPolicy(ConfigManager config){
		if(config.getProperty(PolicyType.getName(PolicyType.cleanUpAfterTimePeriod))!=null){
			TimePeriodPolicy policy = new TimePeriodPolicy();
			policy.setThreshold(config.getProperty(PolicyType.getName(PolicyType.cleanUpAfterTimePeriod)));
			return policy;
		}
		if(config.getProperty(PolicyType.getName(PolicyType.cleanUpAfterFinishTestNumber))!=null){
			FinishTestNumberPolicy policy = new FinishTestNumberPolicy();
			policy.setThreshold(config.getProperty(PolicyType.getName(PolicyType.cleanUpAfterFinishTestNumber)));
			return policy;
		}
		if(config.getProperty(PolicyType.getName(PolicyType.cleanUpAfterResourceUsageReach))!=null){
			ResourceUsagePolicy policy = new ResourceUsagePolicy();
			policy.setThreshold(config.getProperty(PolicyType.getName(PolicyType.cleanUpAfterResourceUsageReach)));
			return policy;
		}
		else{
			return new DefaultPolicy();
		}
	}

}
