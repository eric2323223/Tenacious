package com.sybase.supqa.tenacious;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.Period;
import org.junit.Before;
import org.junit.Test;

public class RftTestResultTest {
	private RftTestResult result;
	
	@Before public void setup(){
		TenaciousConfig cfg = new TenaciousConfig();
		result = new RftTestResult(cfg.getTenaciousRootPath()+"\\src\\test\\fixture\\rational_ft_logframe.html");
	}

	@Test
	public void shouldGetDuration() {
		Period period = result.getExecutePeriod();
		assertEquals(1, period.getMinutes());
		assertEquals(22, period.getSeconds());
	}
	
	@Test
	public void shouldGetFailedVps(){
		List<String> failedVp = result.getFailedVerificationPoints();
		assertEquals(1, failedVp.size());
		assertEquals("[noError]", failedVp.get(0));
	}

}
