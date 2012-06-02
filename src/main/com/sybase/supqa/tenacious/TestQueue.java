package com.sybase.supqa.tenacious;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
					allTests.add(test);
				}
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
