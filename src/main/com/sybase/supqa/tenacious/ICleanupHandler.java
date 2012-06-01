package com.sybase.supqa.tenacious;

import com.sybase.supqa.tenacious.policy.IExecutionPolicy;

public interface ICleanupHandler {
	
	public void handle(IExecutionPolicy policy, RftTestSuiteRunner runner);

}