package com.sybase.supqa.tenacious;

import java.io.File;
import java.util.List;

import org.joda.time.Period;

public class RftTestResult {
	private final String rftLogFile;
	private final Period executePeriod;
	private final List<String> failedVerificationPoints;
	private final String exception;
	
	public RftTestResult(String file) {
		this.rftLogFile = file;
		RftHtmlLogParser parser = new RftHtmlLogParser(file);
		executePeriod = parser.getExecutionPeriod();
		failedVerificationPoints = parser.getFailedVerificationPoints();
		exception = parser.getException();
	}
	
	public boolean isPass() {
		return exception==null && failedVerificationPoints.size()==0;
	}
	
	public List<String> getFailedVerificationPoints(){
		return failedVerificationPoints;
	}
	
	public Period getExecutePeriod(){
		return this.executePeriod;
	}
	
	public String getException(){
		return this.exception;
	}

}
