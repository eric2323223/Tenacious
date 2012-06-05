package com.sybase.supqa.tenacious.util;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Cmd {
	private static final ExecutorService THREAD_POOL 
    = Executors.newCachedThreadPool();

private static <T> T timedCall(Callable<T> c, long timeout, TimeUnit timeUnit)
    throws InterruptedException, ExecutionException, TimeoutException
{
    FutureTask<T> task = new FutureTask<T>(c);
    THREAD_POOL.execute(task);
    return task.get(timeout, timeUnit);
}

try {
    int returnCode = timedCall(new Callable<int>() {
        public int call() throws Exception
        {
            java.lang.Process process = Runtime.getRuntime().exec(command); 
            return process.waitFor();
        }, timeout, TimeUnit.SECONDS);
} }catch (TimeoutException e) {
    // Handle timeout here
}
	
	
	public static void execute(String command){
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
