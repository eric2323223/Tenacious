package com.sybase.supqa.tenacious;

import java.io.File;

public class RftTestResult {
	private final File rftLogFile;

	public RftTestResult(File file) {
		this.rftLogFile = file;
	}
	
	public File getRftLogFile(){
		return this.rftLogFile;
	}

	public boolean isPass() {
		return true;
	}

}
