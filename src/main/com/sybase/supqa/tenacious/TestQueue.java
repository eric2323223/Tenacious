package com.sybase.supqa.tenacious;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TestQueue {
	private File testQueueFile;
	private List<String> allTests;
	private List<String> todoTests;
	private List<String> doneTests;
	
	public TestQueue(String fileName){
		this.testQueueFile = new File(fileName);
		loadTests();
	}
	
	public List<String> getTodoTests(){
		loadTests();
		return todoTests;
	}
	
	public List<String> getDoneTests(){
		loadTests();
		return doneTests;
	}
	
	public void updateTestStatus(RftTestScript script){
		for(int i=0;i<allTests.size();i++){
			if(allTests.get(i).startsWith(script.getName())){
				allTests.set(i, script.toString());
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
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(this.testQueueFile));
			String test;
			while((test=br.readLine())!=null){
				allTests.add(test);
				if(isTodoTest(test)){
					todoTests.add(test);
				}else{
					doneTests.add(test);
				}
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


	private boolean isTodoTest(String test) {
		return test.split("\t").length>1?false:true;
	}
	
}
