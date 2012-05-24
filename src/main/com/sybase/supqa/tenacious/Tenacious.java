package com.sybase.supqa.tenacious;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sybase.supqa.tenacious.util.Cmd;

public class Tenacious {
	
	public static void main(String[] args){
		List<String> tests = loadTestsFromFile("");
		if(tests.size()>0){
			RftTestSuiteRunner runner = new RftTestSuiteRunner();
			runner.runTestSuite(tests);
			List<String> failedTests = loadTestsFromFile("");
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
