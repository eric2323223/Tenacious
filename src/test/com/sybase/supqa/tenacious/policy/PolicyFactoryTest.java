package com.sybase.supqa.tenacious.policy;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sybase.supqa.tenacious.Tenacious;

public class PolicyFactoryTest {
	private PolicyConfig config;
	
	@Before public void setup(){
		config = new PolicyConfig(Tenacious.TENACIOUS_POLICY_CONFIG);
	}

	@Test
	public void shouldCreatePolicyFromPolicyConfig() {
		IExecutionPolicy policy = PolicyFactory.getPolicy(config);
		assertEquals(true, policy instanceof ResourceUsagePolicy);
		assertEquals(2, policy.getThreshold().keySet().size());
	}

}
