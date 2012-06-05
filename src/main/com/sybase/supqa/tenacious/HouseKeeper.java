package com.sybase.supqa.tenacious;

import com.sybase.supqa.tenacious.util.Cmd;

public class HouseKeeper implements ICleanupHandler {

	public void basicCleanup() {
		RftTestScript script = new RftTestScript("testscript.Auxiliary.CleanUp");
		script.run();
		closeIE();
	}

	private void closeIE() {
		Cmd.execute("cmd /c taskkill /F /IM IEXPLORE.EXE /T");
	}

	public void advancedCleanup() {
		System.out.println("close all active dialogs and reset ET perspective...");
	}

	public void ultimateCleanup() {
		Cmd.execute("cmd /c shutdown -f -r -t 10");
		System.out.println("restarting machine....");
		System.exit(0);
	}
}
