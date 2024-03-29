package com.sybase.supqa.tenacious.policy;

import java.util.Hashtable;

import com.sybase.supqa.tenacious.CleanupStatus;
import com.sybase.supqa.tenacious.RftTestSuiteRunner;
import com.sybase.supqa.tenacious.util.SystemMonitor;

public class ResourceUsagePolicy extends DefaultPolicy{

	@Override
	public CleanupStatus getCleanUpStatus(RftTestSuiteRunner runner) {
		Hashtable<String, String> parameters = getThreshold();
		String cpuIdleStr = parameters.get("cpuIdle");
		String memoryFreeStr = parameters.get("memoryFree");
		
		if(cpuIdleStr!=null){
			double cpuIdle = new Double(cpuIdleStr).doubleValue();
			if(cpuIdle > SystemMonitor.getCpuIdlePercentage()){
				System.out.println("ResourceUsagePolicy: cpu="+SystemMonitor.getCpuIdlePercentage());
				return CleanupStatus.ULTIMATE_CLEANUP;
			}
		}
		if(memoryFreeStr!=null){
			long memFree = new Long(memoryFreeStr).longValue();
			if(memFree < SystemMonitor.getMemoryFree()){
				return CleanupStatus.ULTIMATE_CLEANUP;
			}
		}
		return super.getCleanUpStatus(runner);
	}


}
