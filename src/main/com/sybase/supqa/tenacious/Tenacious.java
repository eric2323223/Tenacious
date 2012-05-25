package com.sybase.supqa.tenacious;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.sybase.supqa.tenacious.policy.PolicyFactory;
import com.sybase.supqa.tenacious.util.ConfigManager;

public class Tenacious {
	private final static String PWD;
	static{
		String binaryPath = Tenacious.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("%20", " ");
		System.out.println(binaryPath);
		PWD = binaryPath.substring(0, binaryPath.indexOf("Tenacious")+ "Tenacious".length());
	}
	
	public static final String TENACIOUS_PROPERTIES=PWD+File.separator+"tenacious.properties";
	public static final String TENACIOUS_POLICY_PROPERTIES=PWD+File.separator+"policy.properties";
	public static final String TENACIOUS_TEST_QUEUE = PWD+File.separator+"TestResults"+File.separator+"TestQueue.txt";
	
	public static void main(String[] args){
		List<RftTestScript> tests = loadTestQueue();
		if(tests.size()>0){
			RftTestSuiteRunner runner = new RftTestSuiteRunner();
			ConfigManager config = new ConfigManager(TENACIOUS_POLICY_PROPERTIES);
			runner.runTestSuite(tests, PolicyFactory.getPolicy(config));
			List<RftTestScript> failedTests = loadTestQueue();
			if(tests.size()==failedTests.size()){
				cleanTestQueue();
				return;
			}else{
				HouseKeeper.ultimateClenUp();
			}
		}
	}

	private static void cleanTestQueue() {
		File file = new File(TENACIOUS_TEST_QUEUE);
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write("");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static List<RftTestScript> loadTestQueue() {
		List<RftTestScript> tests = new ArrayList<RftTestScript>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(TENACIOUS_TEST_QUEUE));
			String line;
			while((line=reader.readLine())!=null){
				tests.add(new RftTestScript(line));
			}
			return tests;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	


}
