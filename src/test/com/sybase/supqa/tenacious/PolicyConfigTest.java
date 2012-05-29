package com.sybase.supqa.tenacious;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sybase.supqa.tenacious.policy.PolicyConfig;

public class PolicyConfigTest {
	
	private PolicyConfig config;
	private TenaciousConfig cfg;

	@Before public void setup(){
		cfg = new TenaciousConfig();
		config = new PolicyConfig(cfg.getTenaciousPolicyConfigFile());
	}

	@Test
	public void shouldGetPolicyClassName() {
		assertEquals("com.sybase.supqa.tenacious.policy.ResourceUsagePolicy", config.getPolicyClassName());
	}
	
	@Test
	public void shouldGetParameterValue(){
		assertEquals("300M", config.getParameterValue("memoryFree") );
	}
	
	@Test
	public void shouldGetParameters(){
		List<String> parameters = config.getParameters();
		assertThat(parameters, hasItem("memoryFree"));
		assertThat(parameters, hasItem("cpuIdle"));
	}
	
	@Test
	public void shouldGetCleanupHandlerClassName(){
		assertEquals("com.sybase.supqa.tenacious.HouseKeeper", config.getCleanupHandlerClassName());
	}

}
