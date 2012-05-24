package com.sybase.supqa.tenacious.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
	
	public static final String CONFIG_FILE = "";
	private Properties properties = new Properties();
	
	public ConfigManager(){
		try {
			properties.load(new FileReader(new File(CONFIG_FILE)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getProperty(String pName){
		return properties.getProperty(pName);
	}
	

}
