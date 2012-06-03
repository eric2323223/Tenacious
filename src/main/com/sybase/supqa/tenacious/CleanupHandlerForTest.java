package com.sybase.supqa.tenacious;

import com.sybase.supqa.tenacious.policy.PolicyConfig;
import com.sybase.supqa.tenacious.policy.PolicyFactory;

public class CleanupHandlerForTest implements ICleanupHandler{

	@Override
	public void advancedCleanup() {
		
	}

	@Override
	public void basicCleanup() {
		
	}

	@Override
	public void ultimateCleanup() {
		System.out.println("======================Clean up the environment==================");
//		TenaciousConfig config = new TenaciousConfig();
//		Tenacious tenacious = new Tenacious(config);
//		TestQueue testQueue = new TestQueue(config.getTenaciousTestQueueFile());
//		PolicyConfig policyConfig = new PolicyConfig(config.getTenaciousPolicyConfigFile());
//		tenacious.runTests(testQueue, PolicyFactory.getPolicy(policyConfig));
	}


}
