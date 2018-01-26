package org.corgiking.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTest {

	public static void main(String[] args) {
		FutureTask<String> future = new FutureTask<>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				TimeUnit.SECONDS.sleep(2);
				return "success";
			}
		});
		Thread thread = new Thread(future);
		thread.start();
		try {
			String ret = future.get(2, TimeUnit.SECONDS);
			System.out.println(ret);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			future.cancel(true);
			e.printStackTrace();
		}

	}

}
