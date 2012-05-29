package com.sybase.supqa.tenacious;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Date;

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
		Date date = parser.parseStartTime();
		assertEquals(new Date(), date);
	}

}
