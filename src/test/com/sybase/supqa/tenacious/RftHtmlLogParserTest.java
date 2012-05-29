package com.sybase.supqa.tenacious;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;


public class RftHtmlLogParserTest {
	
	private RftHtmlLogParser parser;
	
	@Before public void setup(){
		parser = new RftHtmlLogParser("");
	}

	@Test public void shouldGetStartTime(){
		Date date = parser.parseStartTime();
		assertEquals();
	}

}
