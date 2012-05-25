package com.sybase.supqa.tenacious;

import java.io.File;

public class RftTestScript {
	private final String name;
	private File logFile;
	
	public String getName() {
		return name;
	}

	public File getLogFile() {
		return logFile;
	}

	public RftTestScript(String name){
		this.name = name;
	}

}
