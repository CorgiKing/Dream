package org.corgiking.proxy.static_proxy;

public class DellComputerProxy extends ComputerProxy {

	public DellComputerProxy(Computer computer) {
		super(computer);
	}

	@Override
	public void preProduce() {
		System.out.println("###----去找戴尔工厂生产电脑----###");
	}

	@Override
	public void afterProduce() {
		System.out.println("###----把戴尔电脑卖给客户-----###");
	}

}
