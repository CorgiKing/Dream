package org.corgiking.design.proxy.static_proxy;

public abstract class ComputerProxy implements Computer {
	
	private Computer computer;
	
	public ComputerProxy(Computer computer) {
		this.computer = computer;
	}
	
	@Override
	public void produce() {
		preProduce();
		computer.produce();
		afterProduce();
	}

	public abstract void preProduce();
	
	public abstract void afterProduce();
	
}
