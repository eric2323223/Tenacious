package com.sybase.supqa.tenacious.policy;

public interface IExecutionPolicy {
	
	public void beforeRunTest();
	public void afterRunTest();
	public void basicCleanUp();
	public void ultimateCleanUp();

}
