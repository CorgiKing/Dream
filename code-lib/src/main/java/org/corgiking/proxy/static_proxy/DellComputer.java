package org.corgiking.proxy.static_proxy;

public class DellComputer implements Computer{
	
	private String brand;
	
	public DellComputer() {
		this.brand = "Dell";
	}
	
	@Override
	public void produce(){
		System.out.println("生产 "+brand + " 电脑");
	}
}
