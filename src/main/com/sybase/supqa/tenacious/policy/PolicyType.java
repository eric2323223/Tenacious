package com.sybase.supqa.tenacious.policy;


public enum PolicyType {
	cleanUpAfterTimePeriod, cleanUpAfterFinishTestNumber, cleanUpAfterResourceUsageReach;
	
	public static String getName(PolicyType type){
		if(type==cleanUpAfterTimePeriod){
			return "cleanUpAfterTimePeriod";
		}
		if(type==cleanUpAfterFinishTestNumber){
			return "cleanUpAfterFinishTestNumber";
		}
		if(type==cleanUpAfterResourceUsageReach){
			return "cleanUpAfterResourceUsageReach";
		}
		throw new RuntimeException();
	}
}
