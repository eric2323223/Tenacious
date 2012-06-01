package com.sybase.supqa.tenacious;


import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.sybase.supqa.tenacious.policy.FinishTestNumberPolicy;
import com.sybase.supqa.tenacious.policy.IExecutionPolicy;
import com.sybase.supqa.tenacious.policy.PolicyConfig;
import com.sybase.supqa.tenacious.policy.PolicyFactory;
import com.sybase.supqa.tenacious.policy.TimePeriodPolicy;

public class PolicyFactoryTest {
	private TenaciousConfig cfg;

	@Before
	public void setUp() throws Exception {
		cfg = new TenaciousConfig();
	}

	@Test public void shouldCreateTimePeriodPolicy(){
		PolicyConfig config = new PolicyConfig(cfg.getTestFixureFolder()+File.separator+"policy_TimePeriod.xml");
		IExecutionPolicy policy = PolicyFactory.getPolicy(config);
		assertEquals(true, policy instanceof TimePeriodPolicy);
		assertEquals("3600", policy.getThreshold().get("timePeriod"));
	}
	
	@Test public void shouldCreateFinishedTestNumberPolicy(){
		PolicyConfig config = new PolicyConfig(cfg.getTestFixureFolder()+File.separator+"policy_FinishedTestNumber.xml");
		IExecutionPolicy policy = PolicyFactory.getPolicy(config);
		assertEquals(true, policy instanceof FinishTestNumberPolicy);
		assertEquals("25", policy.getThreshold().get("finishTestNumber"));
	}
}
