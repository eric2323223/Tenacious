package com.sybase.supqa.tenacious;

import com.sybase.supqa.tenacious.util.Cmd;

public class HouseKeeper {

	public static void basicCleanUp() {
		System.out.println("close all active dialogs and reset ET perspective...");
	}

	public static void advancedClenUp() {
		System.out.println("restarting ET and RFT...");
	}

	public static void ultimateClenUp() {
		Cmd.execute("cmd /c shutdown -f -r -t 3");
		System.out.println("restarting machine....");
		System.exit(0);
	}

}
