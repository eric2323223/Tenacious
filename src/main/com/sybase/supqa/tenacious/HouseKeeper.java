package com.sybase.supqa.tenacious;

public class HouseKeeper {

	public static void basicCleanUp() {
		System.out.println("close all active dialogs and reset ET perspective...");
	}

	public static void advancedClenUp() {
		System.out.println("restarting ET and RFT...");
	}

	public static void ultimateClenUp() {
		System.out.println("restarting machine....");
	}

}
