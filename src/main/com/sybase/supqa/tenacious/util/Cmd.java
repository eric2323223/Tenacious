package com.sybase.supqa.tenacious.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Cmd {
	private static final ExecutorService THREAD_POOL = Executors.newCachedThreadPool();

	private static <T> T timedCall(Callable<T> callable, long timeout,	TimeUnit timeUnit) 
			throws InterruptedException, ExecutionException, TimeoutException 
	{
		FutureTask<T> task = new FutureTask<T>(callable);
		THREAD_POOL.execute(task);
		return task.get(timeout, timeUnit);
	}
	
	public static int executeCommandLine(final String commandLine,
			final long timeout) throws IOException, InterruptedException,
			TimeoutException {
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(commandLine);
		/* Set up process I/O. */
		// ...
		Worker worker = new Worker(process);
		worker.start();
		try {
			worker.join(timeout);
			if (worker.exit != null)
				return worker.exit;
			else
				throw new TimeoutException();
		} catch (InterruptedException ex) {
			worker.interrupt();
			Thread.currentThread().interrupt();
			throw ex;
		} finally {
			process.destroy();
		}
	}

	private static class Worker extends Thread {
		private final Process process;
		private Integer exit;

		private Worker(Process process) {
			this.process = process;
		}

		public void run() {
			try {
				exit = process.waitFor();
			} catch (InterruptedException ignore) {
				return;
			}
		}
	}

	public static void executeCommandLine(final String command, int timeout) throws TimeoutException{
		try {
		    timedCall(new Callable<Integer>() {
		    	@Override
		        public Integer call() throws Exception
		        {
		            java.lang.Process process = Runtime.getRuntime().exec(command); 
		            return new Integer(process.waitFor());
		        }
	        }, timeout, TimeUnit.SECONDS);
		} catch (TimeoutException e){
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
}

	public static void execute(String command) {
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	public static String getOutput(String cmd) {
		try {
			StringBuffer cmdout = new StringBuffer(); 
	        Process process;
				process = Runtime.getRuntime().exec(cmd);
	        InputStream fis = process.getInputStream(); 
	        BufferedReader br = new BufferedReader(new InputStreamReader(fis)); 
	        String line = null; 
	        while ((line = br.readLine()) != null) { 
	                cmdout.append(line+"\n"); 
	        }
	        return cmdout.toString().trim(); 
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get console output");
		}    
	}
	
	public static void main(String [] args) throws IOException{
		System.out.println(Cmd.getOutput("cmd /c tasklist | findstr \"IEXPLORE\""));
		
//		TenaciousConfig config = new TenaciousConfig();
//		Cmd.execute(FileUtil.readFile(config.getTenaciousRootPath()+File.separator+"runScript.bat"));
	}

	public static void executeWithoutWait(String command) {
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeIE() {
		int retryCount = 0;
		while(retryCount < 3){
			String message = getOutput("cmd /c taskkill /F /IM iexplore.exe /T");
//			String message = getOutput("cmd /c taskkill /F /IM IEXPLORE.EXE /T");
			retryCount++;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (message.trim().length()>0){
				return ;
			}
		}
	}
	
}
