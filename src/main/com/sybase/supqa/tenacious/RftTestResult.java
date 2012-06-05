package com.sybase.supqa.tenacious;

import java.util.List;

import org.joda.time.Period;

public class RftTestResult {
	private String rftLogFile;
	private Period executePeriod;
	private List<String> failedVerificationPoints;
	private String exception;
	
	public RftTestResult(String file) {
		this.rftLogFile = file;
		RftHtmlLogParser parser = new RftHtmlLogParser(file);
		executePeriod = parser.getExecutionPeriod();
		failedVerificationPoints = parser.getFailedVerificationPoints();
		exception = parser.getException();
	}
	
	public RftTestResult() {}
	
	public void setException(String exp){
		this.exception = exp;
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
