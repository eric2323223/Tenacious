package com.sybase.supqa.tenacious;

import com.sybase.supqa.tenacious.policy.PolicyConfig;

public class CleanupHandlerFactory {
	
	public static ICleanupHandler getHandler(PolicyConfig config){
		String className = config.getCleanupHandlerClassName();
		try {
			ICleanupHandler handler = (ICleanupHandler)Class.forName(className).newInstance();
			return handler;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to create ICleanupHandler instance");
		}
	}
}
