package com.sybase.supqa.tenacious;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sybase.supqa.tenacious.policy.PolicyConfig;

public class CleanupHandlerFactoryTest {
	private PolicyConfig config;
	private TenaciousConfig cfg;
	
	@Before public void setup(){
		cfg = new TenaciousConfig();
		config = new PolicyConfig(cfg.getTenaciousPolicyConfigFile());
	}

	@Test
	public void shouldCreateCleanupHandler() {
		ICleanupHandler handler = CleanupHandlerFactory.getHandler(config);
		assertEquals(true, handler instanceof HouseKeeper);
	}

}
