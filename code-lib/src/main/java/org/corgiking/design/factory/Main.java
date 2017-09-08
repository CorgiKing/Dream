package org.corgiking.design.factory;

import org.corgiking.design.factory.methodfactory.ComputerFactory;
import org.corgiking.design.factory.methodfactory.DellComputerFactory;
import org.corgiking.design.factory.simplefactory.SimpleComputerFactory;

public class Main {

	/**
	 * 工厂设计模式:
	 * 1.简单工厂
	 * 2.工厂方法
	 * 3.抽象工厂
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//简单工厂
		Computer lenovo = SimpleComputerFactory.create("lenovo");
		lenovo.produce();
		//工厂方法
		ComputerFactory factory = new DellComputerFactory();
		Computer dell = factory.create();
		dell.produce();
		//抽象工厂
		
	}

}
