package org.corgiking.design.singleton;

/**
 * 静态内部类实现单例模式，既能保证线程安全，又可实现懒加载。
 * 
 * @author goaler
 *
 */
public class Singleton {
	private Singleton() {}

    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
