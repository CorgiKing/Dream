package org.corgiking.design.singleton;

public class Singleton2 {
	
	private static volatile Singleton2 instance;
	
	public static Singleton2 getInstance(){
		if (instance == null) {
			synchronized (Singleton2.class) {
				if (instance == null) {
					instance = new Singleton2();
				}
			}
		}
		return instance;
	}
	
	private Singleton2(){
	}
}
