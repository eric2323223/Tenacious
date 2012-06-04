package com.sybase.supqa.tenacious;


public class CleanupHandlerForTest implements ICleanupHandler{

	@Override
	public void advancedCleanup() {
		System.out.println("======================close all windows==================");
	}

	@Override
	public void basicCleanup() {
		
	}

	@Override
	public void ultimateCleanup() {
		System.out.println("======================restart machine==================");
	}


}
