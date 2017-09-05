package org.corgiking.design.proxy.dynamic_proxy;

import org.corgiking.design.proxy.static_proxy.Computer;
import org.corgiking.design.proxy.static_proxy.DellComputer;

public class Main {

	public static void main(String[] args) {
		Computer dell = new DellComputer();
		DynamicProxy<Computer> proxy = new DynamicProxy<Computer>(dell);//此处不能使用DellComputer
		dell = proxy.getProxy();
		dell.produce();
		
	}

}
