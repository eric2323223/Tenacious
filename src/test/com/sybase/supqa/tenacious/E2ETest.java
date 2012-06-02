package com.sybase.supqa.tenacious;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.sybase.supqa.tenacious.policy.PolicyConfig;
import com.sybase.supqa.tenacious.policy.PolicyFactory;
import com.sybase.supqa.tenacious.policy.PolicyType;

public class E2ETest {
	private TenaciousConfig tenaciousConfig;
	private PolicyConfig finishTestNumberPolicyConfig;
	private TestQueue testQueue;

	@Before
	public void setUp() throws Exception {
		tenaciousConfig = new TenaciousConfig();
		testQueue = new TestQueue(tenaciousConfig.getTestFixureFolder()+File.separator+"TestQueue");
		createFinishTestNumberPolicyConfig();
		
	}

	private void createFinishTestNumberPolicyConfig() {
		finishTestNumberPolicyConfig = mock(PolicyConfig.class);
		when(finishTestNumberPolicyConfig.getCleanupHandlerClassName()).thenReturn("com.sybase.supqa.tenacious.CleanupHandlerForTest");
		when(finishTestNumberPolicyConfig.getPolicyClassName()).thenReturn("com.sybase.supqa.tenacious.policy.FinishTestNumberPolicy");
		when(finishTestNumberPolicyConfig.getParameterValue(PolicyType.FINISHED_TEST_NUMBER)).thenReturn("2");
	}
	
	@Test
	public void shouldFinishAllTests(){
		Tenacious tenacious = new Tenacious(tenaciousConfig);
		tenacious.runTests(testQueue, PolicyFactory.getPolicy(finishTestNumberPolicyConfig));
	}

}
