package com.sybase.supqa.tenacious;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sybase.supqa.tenacious.policy.PolicyConfig;

public class CleanupHandlerFactoryTest {
	private PolicyConfig config;
	
	@Before public void setup(){
		config = new PolicyConfig(Tenacious.TENACIOUS_POLICY_CONFIG);
	}

	@Test
	public void shouldCreateCleanupHandler() {
		ICleanupHandler handler = CleanupHandlerFactory.getHandler(config);
		assertEquals(true, handler instanceof HouseKeeper);
	}

}
