package com.sybase.supqa.tenacious;


public class RftTestRunner {
	public static final String RFT_JR_PATH = "";
	public static final String JRE_PATH = "";
	public static final String REPORT_PATH = "";
	
	public RftTestResult runTest(RftTestScript test){
//		String commandLine = "cmd /c start/wait ";
//		Cmd.execute(commandLine);
		System.out.println("run test "+test.getName());
		return new RftTestResult(test.getLogFile());
	}

}
