package com.sybase.supqa.tenacious.policy;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sybase.supqa.tenacious.Tenacious;
import com.sybase.supqa.tenacious.TenaciousConfig;

public class PolicyFactoryTest {
	private TenaciousConfig cfg;
	private PolicyConfig config;
	
	@Before public void setup(){
		cfg = new TenaciousConfig();
		config = new PolicyConfig(cfg.getTenaciousPolicyConfigFile());
	}

	@Test
	public void shouldCreatePolicyFromPolicyConfig() {
		IExecutionPolicy policy = PolicyFactory.getPolicy(config);
		assertEquals(true, policy instanceof ResourceUsagePolicy);
		assertEquals(2, policy.getThreshold().keySet().size());
	}

}
