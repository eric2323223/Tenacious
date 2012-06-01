package com.sybase.supqa.tenacious;

import com.sybase.supqa.tenacious.policy.IExecutionPolicy;
import com.sybase.supqa.tenacious.util.Cmd;

public class HouseKeeper implements ICleanupHandler {

	public void basicCleanup() {
		System.out.println("restarting ET and RFT...");
	}

	public void advancedCleanup() {
		System.out.println("close all active dialogs and reset ET perspective...");
	}

	public void ultimateCleanup() {
		Cmd.execute("cmd /c shutdown -f -r -t 10");
		System.out.println("restarting machine....");
		System.exit(0);
	}

	@Override
	public void handle(IExecutionPolicy policy, RftTestSuiteRunner runner) {
		if (policy.getCleanUpStatus(runner) == CleanUpStatus.BASIC_CLEANUP) {
			basicCleanup();
		}
		if (policy.getCleanUpStatus(runner) == CleanUpStatus.ADVANCED_CLEANUP) {
			advancedCleanup();
		}
		if (policy.getCleanUpStatus(runner) == CleanUpStatus.ULTIMATE_CLEANUP) {
			ultimateCleanup();
		}

	}
}
