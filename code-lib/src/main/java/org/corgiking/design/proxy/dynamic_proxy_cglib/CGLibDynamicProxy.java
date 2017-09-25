package org.corgiking.design.proxy.dynamic_proxy_cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibDynamicProxy implements MethodInterceptor {
	private Object target;

	public CGLibDynamicProxy(Object target) {
		this.target = target;
	}

	public Object getProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(this);
		Object obj = enhancer.create();
		return obj;
	}

	@Override
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		Object res;

		preHandle();

		res = method.invoke(target, args);

		afterHandle();

		return res;
	}

	public void preHandle() {
		System.out.println("方法执行前....");
	}

	public void afterHandle() {
		System.out.println("方法执行后....");
	}
}
