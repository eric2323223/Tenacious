package com.sybase.supqa.tenacious;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestQueue {
	private File testQueueFile;
	private List<String> allTests = new ArrayList<String>();
	
	public TestQueue(String fileName){
		this.testQueueFile = new File(fileName);
		loadTests();
	}
	
	public List<String> getTodoTests(){
		loadTests();
		List<String> todoTests = new ArrayList<String>();
		for(String test:allTests){
			if(isTodoTest(test)){
				todoTests.add(test);
			}
		}
		return todoTests;
	}

	public List<String> getDoneTests(){
		loadTests();
		List<String> doneTests = new ArrayList<String>();
		for(String test:allTests){
			if(!isTodoTest(test)){
				doneTests.add(test);
			}
		}
		return doneTests;
	}
	
	public void updateTestStatus(RftTestScript script){
		for(int i=0;i<allTests.size();i++){
			if(allTests.get(i).split("\t")[0].equals(script.getName())){
				allTests.set(i, script.toString());
				break;
			}
		}
		persist();
	}
	
	private void persist(){
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(this.testQueueFile));
			for(String test:allTests){
				bw.write(test+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void loadTests(){
		if(!isLoaded()){
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(this.testQueueFile));
				String test;
				while((test=br.readLine())!=null){
					if(test.contains("[") && test.contains("]")){
						loadMatchTests(test);
					}else{
						if(!test.trim().equals("")){
							allTests.add(test);
						}
					}
				}
				persist();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void loadMatchTests(String test) {
		String newTest = test.replace("[", "").replace("]", "");
		List<String> tests = getMatchTests(newTest);
		allTests.remove(test);
		for(String t:tests){
			allTests.add(t);
		}
	}

	//[testscript.codegen.script.*]
	//[testscript.codegen.script.mbo*]
	//[testscript.codegen.script.*-E2E]
	List<String> getMatchTests(String test) {
		TenaciousConfig config = new TenaciousConfig();
		List<String> tests = new ArrayList<String>();
		String folder = getFeatureFolder(test);
		String matchPattern = getMatchPattern(test);
		List<String> allTestsInFolder = config.getTestScriptsInFolder(folder);
		for(String t:allTestsInFolder){
			if(match(t, matchPattern)){
				tests.add(buildTestScriptName(folder, t));
			}
		}
		return tests;
	}

	private String buildTestScriptName(String folder, String t) {
		return folder.replace("\\", ".")+"."+t;
	}

	boolean match(String t, String matchPattern) {
		if(matchPattern.contains("*")){
			if(matchPattern.equals("*")){
				return true;
			}else{
				if(matchPattern.endsWith("*")){
					String pattern = matchPattern.replace("*", "");
					if(t.startsWith(pattern)){
						return true;
					}
				}
				if(matchPattern.startsWith("*")){
					String pattern = matchPattern.replace("*", "");
					if(t.endsWith(pattern)){
						return true;
					}
				}
				if(matchPattern.contains("*-")){
					String pattern = matchPattern.substring(0, matchPattern.indexOf("*-"));
					String excludePattern = matchPattern.substring(matchPattern.indexOf("*-")+2);
					if(t.startsWith(pattern) && !t.contains(excludePattern)){
						return true;
					}
				}
			}
			return false;
		}else{
			throw new RuntimeException("Unrecognized match pattern: "+matchPattern);
		}
	}

	String getMatchPattern(String text) {
		return text.substring(text.lastIndexOf(".")+1);
	}

	String getFeatureFolder(String text) {
		return text.substring(0, text.lastIndexOf(".")).replace(".", "\\");
	}

	private boolean isLoaded() {
		return allTests.size()>0;
	}

	private boolean isTodoTest(String test) {
		return test.split("\t").length>1?false:true;
	}

	public void clear() {
		allTests.clear();
		persist();
	}

	public List<String> getAllTests() {
		loadTests();
		return this.allTests;
	}
	
}
