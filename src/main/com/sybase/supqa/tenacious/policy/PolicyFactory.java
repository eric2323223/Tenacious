package com.sybase.supqa.tenacious.policy;

import com.sybase.supqa.tenacious.util.ConfigManager;

public class PolicyFactory {
	
	public static IExecutionPolicy getPolicy(ConfigManager config){
		if(config.getProperty(PolicyType.getName(PolicyType.cleanUpAfterTimePeriod))!=null){
			return new TimePeriodPolicy();
		}
		if(config.getProperty(PolicyType.getName(PolicyType.cleanUpAfterFinishTestNumber))!=null){
			return new FinishTestNumberPolicy();
		}
		if(config.getProperty(PolicyType.getName(PolicyType.cleanUpAfterResourceUsageReach))!=null){
			return new ResourceUsagePolicy();
		}
		else{
			return new DefaultPolicy();
		}
	}

}
