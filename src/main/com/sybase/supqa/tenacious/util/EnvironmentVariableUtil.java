package com.sybase.supqa.tenacious.util;

public class EnvironmentVariableUtil {
	public static String getVariable(String varName){
		return System.getenv(varName);
	}
	
	public static void main(String[] args){
//		System.out.println(System.getEnv("JAVA_HOME"));
//		System.out.println(System.getenv("JAVA_HOME"));
	}
}
