package com.sybase.supqa.tenacious;

import java.io.File;

import com.sybase.supqa.tenacious.util.PropertiesFileHelper;

public class TenaciousConfig {
	
	private String pwd;
	
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
		return getTenaciousRootPath()+File.separator+"TestResults"+File.separator+"TestQueue.txt";
	}
	
	public String getSuptafRootPath(){
		PropertiesFileHelper helper = new PropertiesFileHelper(getTenaciousPropertiesFile());
		return helper.getProperty("SUPTAF_ROOT_PATH");
	}
	
	public static void main(String[] args){
		System.out.println(new TenaciousConfig().getTenaciousRootPath());
	}

}
