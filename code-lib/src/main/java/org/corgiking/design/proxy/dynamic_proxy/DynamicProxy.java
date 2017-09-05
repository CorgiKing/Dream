package org.corgiking.design.proxy.dynamic_proxy;

public class DynamicProxy<T> extends AbstractDynamicProxy<T> {

	public DynamicProxy(T target) {
		super(target);
	}

	@Override
	public void preHandle() {
		System.out.println("方法执行前...");
	}

	@Override
	public void afterHandle() {
		System.out.println("方法执行后...");
	}

}
