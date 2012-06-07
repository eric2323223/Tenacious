package com.sybase.supqa.tenacious.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtil {
	
	public static void copyFile(String sourceFile, String destFile) throws IOException {
	    if(!new File(destFile).exists()) {
	        new File(destFile).createNewFile();
	    }
	    FileChannel source = null;
	    FileChannel destination = null;

	    try {
	        source = new FileInputStream(sourceFile).getChannel();
	        destination = new FileOutputStream(destFile).getChannel();
	        destination.transferFrom(source, 0, source.size());
	    }
	    finally {
	        if(source != null) {
	            source.close();
	        }
	        if(destination != null) {
	            destination.close();
	        }
	    }
	}
	
	public static String readFile(String fileName){
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String content="";
			String line;
			while((line = br.readLine())!=null){
				content = content+line;
			}
			return content;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("oops");
		}
	}

}
