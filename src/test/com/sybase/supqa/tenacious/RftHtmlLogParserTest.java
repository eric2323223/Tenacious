package com.sybase.supqa.tenacious;

import static org.junit.Assert.assertEquals;

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
		assertEquals("25-May-2012 06:04:00.687 PM", date);
	}
	
	@Test public void shouldGetEndTime(){
		String date = parser.getEndTimeString();
		assertEquals("25-May-2012 07:25:25.421 PM", date);
	}
	
	@Test public void shouldGetNotes(){
		assertEquals(10, parser.getAllNotes().size());
	}
	
	@Test public void shouldGetVerificationPoints(){
		List<String> vps = parser.getVerificationPoints();
		assertEquals("[noError] failed.", vps.get(0));
	}
	
	@Test public void shouldGetFailures(){
		assertEquals(5, parser.getAllFailures().size());
	}
	
	@Test public void shouldGetFailedVps(){
		assertEquals(1, parser.getFailedVerificationPoints().size());
		assertEquals("[errorScreen] passed.", parser.getFailedVerificationPoints().get(0));
	}
	
	@Test public void shouldGetExecutionPeriod(){
		Period period = parser.getExecutionPeriod();
		assertEquals(0, period.getHours());
		assertEquals(0, period.getMinutes());
		assertEquals(0, period.getSeconds());
	}
	
	@Test public void shouldParseDateString(){
		Date date = parser.parseDateString("25-May-2012 07:25:25.421 PM");
//		Date date = parser.parseDateString("25-May-2012 07:25:25.421 PM");
//		assertEquals(2012, date.getYear());
		assertEquals(25, date.getDate());
		assertEquals(5, date.getMonth());
	}
	
	@Test public void shouldConvertMonth(){
		assertEquals("05", parser.convertMonth("May"));
	}

}
