package org.corgiking.design.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class AbstractDynamicProxy<T> implements InvocationHandler {

	private T target;

	public AbstractDynamicProxy(T target) {
		this.target = target;
	}

	@SuppressWarnings("unchecked")
	public T getProxy() {

		Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				this);

		return (T) proxy;
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
