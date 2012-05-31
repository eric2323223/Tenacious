package com.sybase.supqa.tenacious.util;

import java.io.IOException;

public class Cmd {
	
	public static void execute(String command){
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			System.out.println("command terminated!");
		} catch (IOException e) {
			e.printStackTrace();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
