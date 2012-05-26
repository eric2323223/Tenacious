package com.sybase.supqa.tenacious.util;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class SystemMonitor {
	
	public static double getCpuIdlePercentage(){
		try {
			Sigar sigar = new Sigar();
            CpuPerc perc = sigar.getCpuPerc();
            return perc.getIdle();
       } catch (SigarException se) {
    	   	se.printStackTrace();
            throw new RuntimeException("Failed to get CPU status");
       }
	}
	
	public static long getMemoryFree(){
		try {
			Sigar sigar = new Sigar();
			Mem mem = sigar.getMem();
			return mem.getFree()/(1024*1024);
		} catch (SigarException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get Memory status");
		}
	}
}
