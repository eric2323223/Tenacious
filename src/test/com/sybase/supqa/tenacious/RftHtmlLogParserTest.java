package com.sybase.supqa.tenacious;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.Period;
import org.junit.Before;
import org.junit.Test;


public class RftHtmlLogParserTest {
	
	private RftHtmlLogParser parser;
	private TenaciousConfig cfg;
	
	@Before public void setup(){
		cfg = new TenaciousConfig();
		String testLogFile = cfg.getTenaciousRootPath()+"\\src\\test\\fixture\\rational_ft_logframe.html";
		parser = new RftHtmlLogParser(testLogFile);
	}

	@Test public void shouldGetStartTime(){
		String date = parser.getStartTimeString();
		assertEquals("16-Mar-2012 03:05:45.724 PM", date);
	}
	
	@Test public void shouldGetEndTime(){
		String date = parser.getEndTimeString();
		assertEquals("16-Mar-2012 03:07:08.380 PM", date);
	}
	
	@Test public void shouldGetNotes(){
		assertEquals(7, parser.getAllNotes().size());
	}
	
	@Test public void shouldGetVerificationPoints(){
		List<String> vps = parser.getVerificationPoints();
		assertEquals("[noError] failed.", vps.get(0));
	}
	
	@Test public void shouldGetFailures(){
		assertEquals(2, parser.getAllFailures().size());
	}
	
	@Test public void shouldGetFailedVps(){
		assertEquals(1, parser.getFailedVerificationPoints().size());
		assertEquals("[noError]", parser.getFailedVerificationPoints().get(0));
	}
	
	@Test public void shouldGetExecutionPeriod(){
		Period period = parser.getExecutionPeriod();
		assertEquals(0, period.getHours());
		assertEquals(1, period.getMinutes());
		assertEquals(22, period.getSeconds());
	}
	
	@Test public void shouldParseDateString(){
		Date date = parser.parseDateString("25-May-2012 07:25:25.421 PM");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		assertEquals(2012, cal.get(Calendar.YEAR));
		assertEquals(25, cal.get(Calendar.DAY_OF_MONTH));
		assertEquals(25, cal.get(Calendar.SECOND));
		assertEquals(5, cal.get(Calendar.MONTH));
	}
	
	@Test public void shouldConvertMonth(){
		assertEquals("05", parser.convertMonth("May"));
	}

}
