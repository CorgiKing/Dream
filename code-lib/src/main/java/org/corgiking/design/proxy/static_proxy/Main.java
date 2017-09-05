package org.corgiking.design.proxy.static_proxy;

public class Main {

	public static void main(String[] args) {
		DellComputer dell = new DellComputer();
		DellComputerProxy dellProxy = new DellComputerProxy(dell);
		
		dellProxy.produce();
	}

}
