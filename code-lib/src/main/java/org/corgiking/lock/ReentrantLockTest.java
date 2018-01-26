package org.corgiking.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

	public static void main(String[] args) throws InterruptedException {
		ReentrantLock reentrantLock = new ReentrantLock();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				reentrantLock.lock();
				try{
					long s = System.currentTimeMillis();
					for(;;){
						System.out.println(Thread.currentThread()+"---"+System.nanoTime());
						TimeUnit.SECONDS.sleep(1);
						if (System.currentTimeMillis() - s >= 10000) {
							break;
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					reentrantLock.unlock();
				}
				
			}
		}).start();
		
		TimeUnit.SECONDS.sleep(1);
		
		boolean b = reentrantLock.tryLock();
		if (b) {
			try{
				long s = System.currentTimeMillis();
				for(;;){
					System.out.println(Thread.currentThread()+"---"+System.nanoTime());
					TimeUnit.SECONDS.sleep(1);
					if (System.currentTimeMillis() - s >= 10000) {
						break;
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				reentrantLock.unlock();
			}
		}
		
		System.out.println("main end");
		
	}

}
