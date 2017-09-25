package org.corgiking.design.proxy.dynamic_proxy_jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class AbstractDynamicProxy implements InvocationHandler {

	private Object target;

	public AbstractDynamicProxy(Object target) {
		this.target = target;
	}

	public Object getProxy() {

		Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				this);

		return proxy;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		preHandle();

		Object res = method.invoke(target, args);

		afterHandle();

		return res;
	}

	public abstract void preHandle();

	public abstract void afterHandle();

}
