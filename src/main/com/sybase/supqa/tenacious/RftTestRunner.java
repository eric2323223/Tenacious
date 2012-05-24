package com.sybase.supqa.tenacious;

import java.io.File;
import java.util.Date;

import com.sybase.supqa.tenacious.util.Cmd;

public class RftTestRunner {
	public static final String RFT_JR_PATH = "";
	public static final String JRE_PATH = "";
	public static final String REPORT_PATH = "";
	
	public RftTestResult runTest(String scriptName){
		String commandLine = "cmd /c start/wait ";
		Date start = new Date();
		Cmd.execute(commandLine);
		Date end = new Date();
		return new RftTestResult(start, end, new File(REPORT_PATH));
	}

}
