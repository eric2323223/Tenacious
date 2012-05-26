package com.sybase.supqa.tenacious;

import java.util.Map;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class Main {

     private static Sigar sigar = new Sigar();

     public static void main(String[] args) {
          getInformationsAboutCPU();
          getInformationsAboutMemory();
//          getInformationsAboutFileSystem();
     }

     /* Method to get Informations about the CPU(s): */
     public static void getInformationsAboutCPU() {
          try {
               CpuPerc perc = sigar.getCpuPerc();
               System.out.println("CPU Idle: "+perc.getIdle());
          } catch (SigarException se) {
               se.printStackTrace();
          }

     }

     /* Method to get Informations about the Memory: */
     public static void getInformationsAboutMemory() {
          System.out.println("**************************************");
          System.out.println("*** Informations about the Memory: ***");
          System.out.println("**************************************\n");

          Mem mem = null;
          try {
               mem = sigar.getMem();
          } catch (SigarException se) {
               se.printStackTrace();
          }

          Map map = mem.toMap();
          System.out.println(map);

          System.out.println("\nPretty printed:");
          System.out.println("---------------");
          System.out.println("Actual total free system memory: "
                    + mem.getActualFree() / 1024 + " KB");
          System.out.println("Actual total used system memory: "
                    + mem.getActualUsed() / 1024 + " KB");
          System.out.println("Total free system memory ......: " + mem.getFree()
                    / 1024 + " KB");
          System.out.println("System Random Access Memory....: " + mem.getRam()
                    + " MB");
          System.out.println("Total system memory............: " + mem.getTotal()
                    / 1024 + " KB");
          System.out.println("Total used system memory.......: " + mem.getUsed()
                    / 1024 + " KB");

          System.out.println("\n**************************************\n");
     }

     /* Method to get Informations about the FileSystem: */
     public static void getInformationsAboutFileSystem() {
          System.out.println("******************************************");
          System.out.println("*** Informations about the FileSystem: ***");
          System.out.println("******************************************\n");

          FileSystem[] filesystem = null;
          try {
               filesystem = sigar.getFileSystemList();
          } catch (SigarException se) {
               se.printStackTrace();
          }

          System.out.println("---------------------");
          System.out.println("Sigar found " + filesystem.length + " drives!");
          System.out.println("---------------------");

          for (int i = 0; i<filesystem.length; i++) {
               Map map = filesystem[i].toMap();
               System.out.println("drive " + i + ": " + map);
          }

          System.out.println("\n******************************************");
     }

}
