package com.sybase.supqa.tenacious;

import com.sybase.supqa.tenacious.util.Cmd;

public class HouseKeeper implements ICleanupHandler {

	@Override
	public void basicCleanup() {
		System.out.println("restarting ET and RFT...");
	}

	@Override
	public void advancedCleanup() {
		// TODO Auto-generated method stub
		System.out.println("close all active dialogs and reset ET perspective...");
	}

	@Override
	public void ultimateCleanup() {
		// TODO Auto-generated method stub
		Cmd.execute("cmd /c shutdown -f -r -t 3");
		System.out.println("restarting machine....");
		System.exit(0);
	}
}
