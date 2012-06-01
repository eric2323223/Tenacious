package com.sybase.supqa.tenacious.policy;

import java.io.File;
import java.util.List;

import com.sybase.supqa.tenacious.TenaciousConfig;

public class PolicyFactory {
	
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

	public static void main(String[] args){
		TenaciousConfig cfg = new TenaciousConfig();
		PolicyConfig config = new PolicyConfig(cfg.getTestFixureFolder()+File.separator+"policy_TimePeriod.xml");
		PolicyFactory.getPolicy(config);
	}

}
