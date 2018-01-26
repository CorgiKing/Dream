package org.corgiking.thread;

import java.util.concurrent.Executors;

public class ThreadPoolTest {

	public static void main(String[] args) {
		Executors.newCachedThreadPool();
		Executors.newFixedThreadPool(10);
		Executors.newScheduledThreadPool(10);
		Executors.newSingleThreadExecutor();
		Executors.newWorkStealingPool();
	}

}
