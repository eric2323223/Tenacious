package com.sybase.supqa.tenacious.util;

public class EnvironmentVariableUtil {
	public static String getVariable(String varName){
		return System.getenv(varName);
	}
	
}
