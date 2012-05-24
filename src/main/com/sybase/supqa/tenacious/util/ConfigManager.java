package com.sybase.supqa.tenacious.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
	
	private Properties properties = new Properties();
	
	public ConfigManager(String config_file){
		try {
			properties.load(new FileReader(new File(config_file)));
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
