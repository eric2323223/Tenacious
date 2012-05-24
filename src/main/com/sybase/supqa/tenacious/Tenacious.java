package com.sybase.supqa.tenacious;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sybase.supqa.tenacious.policy.PolicyFactory;
import com.sybase.supqa.tenacious.util.Cmd;
import com.sybase.supqa.tenacious.util.ConfigManager;

public class Tenacious {
	private final static String PWD;
	static{
		PWD = new File(".").getAbsolutePath();
	}
	
	public static final String TENACIOUS_PROPERTIES=PWD+File.pathSeparator+"tenacious.properties";
	public static final String TENACIOUS_POLICY_PROPERTIES=PWD+File.pathSeparator+"policy.properties";
	public static final String TENACIOUS_TESTCASES_LOG = PWD+File.pathSeparator+"TestResult"+File.pathSeparator+"testcases.txt";
	
	public static void main(String[] args){
		
		List<String> tests = loadTestsFromFile(TENACIOUS_TESTCASES_LOG);
		if(tests.size()>0){
			RftTestSuiteRunner runner = new RftTestSuiteRunner();
			ConfigManager config = new ConfigManager(TENACIOUS_POLICY_PROPERTIES);
			runner.runTestSuite(tests, PolicyFactory.getPolicy(config));
			List<String> failedTests = loadTestsFromFile(TENACIOUS_TESTCASES_LOG);
			if(tests.size()==failedTests.size()){
				return;
			}else{
				restartMachine();
			}
		}
	}

	private static void restartMachine() {
		Cmd.execute("cmd /c shutdown -f -r -t 0");
	}

	private static List<String> loadTestsFromFile(String string) {
		List<String> tests = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(string));
			String line;
			while((line=reader.readLine()).trim().length()>0){
				tests.add(line);
			}
			return tests;
		} catch (IOException e) {
			return tests;
		} finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	


}
