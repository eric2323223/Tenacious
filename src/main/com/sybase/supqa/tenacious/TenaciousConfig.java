package com.sybase.supqa.tenacious;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sybase.supqa.tenacious.util.PropertiesFileHelper;

public class TenaciousConfig {
	
	private String pwd;
	private PropertiesFileHelper helper = new PropertiesFileHelper(getTenaciousPropertiesFile());
	
	public String getTenaciousRootPath() {
		if(pwd==null){
			String binaryPath = Tenacious.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("%20", " ").replace("/", "\\");
			if(binaryPath.startsWith("\\")){
				binaryPath = binaryPath.substring(1);
			}
			pwd = binaryPath.substring(0, binaryPath.indexOf("Tenacious")+ "Tenacious".length());
		}
		return pwd;
	}
	
	public String getTenaciousPropertiesFile() {
		return getTenaciousRootPath()+File.separator+"tenacious.properties";
	}
	
	public String getTenaciousPolicyConfigFile() {
		return getTenaciousRootPath()+File.separator+"policy.xml";
	}
	
	public String getTenaciousTestQueueFile() {
		return getTenaciousRootPath()+File.separator+"TestResults"+File.separator+"TestQueue";
	}
	
	public String getSuptafRootPath(){
		return helper.getProperty("SUPTAF_ROOT_PATH");
	}
	
	public String getSuptafLogRootPath(){
		return helper.getProperty("SUPTAF_LOG_ROOT_PATH");
	}
	
	public static void main(String[] args){
		System.out.println(new TenaciousConfig().getSuptafRootPath());
	}

	public String getRftFtJar() {
		return helper.getProperty("RFT_FT_PATH");
	}

	public String getTestFixureFolder() {
		return getTenaciousRootPath()+ File.separator+"src"+File.separator+"test"+File.separator+"fixture";
	}
	
	public String getWindowsStartupFolder(){
		return helper.getProperty("STARTUP_FOLDER");
	}

	public String getDefaultTestQueue() {
		return getTenaciousRootPath()+ File.separator+"testResults"+File.separator+"TestQueue";
	}
	
	public List<String> getTestScriptsInFolder(String path){
		List<String> testScripts = new ArrayList<String>();
		String fullPath = getSuptafRootPath()+File.separator+path;
		File folder = new File(fullPath);
		if(folder.exists() && folder.isDirectory()){
			String[] allFiles = folder.list();
			for(String file:allFiles){
				if(file.endsWith(".testsuite")){
					if(!file.toLowerCase().contains("all.testsuite")){
						testScripts.add(file.replace(".testsuite", ""));
					}
				}
			}
		}
		return testScripts;
	}

}
