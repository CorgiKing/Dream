package org.corgiking.design.proxy.dynamic_proxy_jdk;

public class DynamicProxy extends AbstractDynamicProxy {

	public DynamicProxy(Object target) {
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
