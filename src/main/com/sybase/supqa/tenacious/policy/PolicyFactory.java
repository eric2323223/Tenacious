package com.sybase.supqa.tenacious.policy;

import java.util.List;

import com.sybase.supqa.tenacious.PolicyConfig;
import com.sybase.supqa.tenacious.util.ConfigManager;

public class PolicyFactory {
	
	public static IExecutionPolicy getPolicy(ConfigManager config){
		if(config.getProperty(PolicyType.TIME_PERIOD)!=null){
			TimePeriodPolicy policy = new TimePeriodPolicy();
			policy.addThreshold(PolicyType.TIME_PERIOD, config.getProperty(PolicyType.TIME_PERIOD));
			return policy;
		}
		if(config.getProperty(PolicyType.FINISHED_TEST_NUMBER)!=null){
			FinishTestNumberPolicy policy = new FinishTestNumberPolicy();
			policy.addThreshold(PolicyType.FINISHED_TEST_NUMBER, config.getProperty(PolicyType.FINISHED_TEST_NUMBER));
			return policy;
		}
		if(config.getProperty(PolicyType.RESOURCE_USAGE)!=null){
			ResourceUsagePolicy policy = new ResourceUsagePolicy();
			String value = config.getProperty(PolicyType.RESOURCE_USAGE);
			for(String keyValue:value.split(",")){
				String k = keyValue.split(":")[0];
				String v = keyValue.split(":")[1];
				policy.addThreshold(k, v);
			}
			return policy;
		}
		else{
			return new DefaultPolicy();
		}
	}
	
	public static IExecutionPolicy getPolicy(PolicyConfig config){
		String className = config.getPolicyClassName();
		try {
			IExecutionPolicy policy = (IExecutionPolicy)Class.forName(className).newInstance();
			List<String> parameters = config.getParameters();
			for(String parameter:parameters){
				policy.addThreshold(parameter, config.getParameterValue(parameter));
			}
			return policy;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to create IExecutionPolicy instance");
		}
	}

}
