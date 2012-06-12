package com.sybase.supqa.tenacious.util;

public class StringUtil {
	
	public static String quote(String text){
		if(text.contains(" ")){
			return "\""+text+"\"";
		}else{
			return text;
		}
	}

}
