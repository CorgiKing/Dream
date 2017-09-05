package org.corgiking.proxy;

import org.corgiking.proxy.static_proxy.DellComputer;
import org.corgiking.proxy.static_proxy.DellComputerProxy;

public class Main {

	public static void main(String[] args) {
		DellComputer dell = new DellComputer();
		DellComputerProxy dellProxy = new DellComputerProxy(dell);
		
		dellProxy.produce();
	}

}
