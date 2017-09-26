package org.corgiking.design.proxy.dynamic_proxy_jdk;

import org.corgiking.design.proxy.static_proxy.Computer;
import org.corgiking.design.proxy.static_proxy.DellComputer;

public class Main {

	public static void main(String[] args) {
		Computer dell = new DellComputer();
		DynamicProxy proxy = new DynamicProxy(dell);//此处不能使用DellComputer
		dell = (Computer) proxy.getProxy();
		dell.produce();
		
		DellComputer dell2 = new DellComputer();
		DynamicProxy proxy2 = new DynamicProxy(dell2);//此处不能使用DellComputer
		Computer dellProxy = (Computer) proxy2.getProxy();
		dellProxy.produce();
		
	}

}
