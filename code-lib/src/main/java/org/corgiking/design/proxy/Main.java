package org.corgiking.design.proxy;

import org.corgiking.design.proxy.static_proxy.DellComputer;
import org.corgiking.design.proxy.static_proxy.DellComputerProxy;

public class Main {

	public static void main(String[] args) {
		DellComputer dell = new DellComputer();
		DellComputerProxy dellProxy = new DellComputerProxy(dell);
		
		dellProxy.produce();
	}

}
