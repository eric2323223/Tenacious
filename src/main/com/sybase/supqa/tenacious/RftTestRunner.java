package com.sybase.supqa.tenacious;


public class RftTestRunner {
	public RftTestResult runTest(RftTestScript test){
//		String commandLine = "cmd /c start/wait ";
//		Cmd.execute(commandLine);
		System.out.println("run test "+test.getName());
		return new RftTestResult(test.getLogFile());
	}

}
