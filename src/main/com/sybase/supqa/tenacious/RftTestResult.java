package com.sybase.supqa.tenacious;

import java.io.File;
import java.util.Date;

import org.joda.time.Period;

public class RftTestResult {
	private final Date start;
	private final Date end;
	private final Period duration;
	private final File rftLogFile;

	public RftTestResult(Date start, Date end, File file) {
		this.start = start;
		this.end = end;
		this.duration = new Period(end.getTime() - end.getTime());
		this.rftLogFile = file;
	}

	public boolean isPass() {
		return true;
	}

}
