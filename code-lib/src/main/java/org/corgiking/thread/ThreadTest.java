package org.corgiking.thread;

public class ThreadTest {

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				for (;;) {
					System.out.println("running");
					if (Thread.interrupted()) {
						break;
					}
				}
			}
		});

		thread.start();
		thread.interrupt();
	}

}
