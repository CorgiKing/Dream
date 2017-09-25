package org.corgiking.design.proxy.dynamic_proxy_cglib;

import org.corgiking.design.proxy.static_proxy.DellComputer;

public class Main {

	public static void main(String[] args) {
		DellComputer dell = new DellComputer();
		CGLibDynamicProxy proxy = new CGLibDynamicProxy(dell);
		dell = (DellComputer) proxy.getProxy();
		dell.produce();
		
		DellComputer dell2 = new DellComputer();
		CGLibDynamicProxy proxy2 = new CGLibDynamicProxy(dell2);
		dell2 = (DellComputer) proxy2.getProxy();
		dell2.produce();
		
		dell.produce();
	}

}
