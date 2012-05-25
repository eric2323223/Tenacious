package com.sybase.supqa.tenacious.util;

public class EnvironmentVariableUtil {
	public static String getVariable(String varName){
		return System.getenv(varName);
	}
	
	public static void main(String[] args){
		System.out.println(System.getProperties());
		System.out.println(System.getProperty("sun.boot.library.path"));
	}
}
