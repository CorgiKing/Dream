package org.corgiking.design.factory;

import org.corgiking.design.factory.abstractfactory.Factory;
import org.corgiking.design.factory.abstractfactory.LenovoFactory;
import org.corgiking.design.factory.methodfactory.ComputerFactory;
import org.corgiking.design.factory.methodfactory.DellComputerFactory;
import org.corgiking.design.factory.product.Computer;
import org.corgiking.design.factory.product.Keyboard;
import org.corgiking.design.factory.product.Mouse;
import org.corgiking.design.factory.simplefactory.SimpleComputerFactory;

public class Main {

	/**
	 * 工厂设计模式:http://blog.csdn.net/wangwenhui11/article/details/3955125
	 * 1.简单工厂:不符合 开闭原则
	 * 2.工厂方法:面向单个产品
	 * 3.抽象工厂:面向多个产品
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//简单工厂
		Computer lenovo = SimpleComputerFactory.create("lenovo");
		lenovo.say();
		
		//工厂方法
		ComputerFactory factory = new DellComputerFactory();
		Computer dell = factory.create();
		dell.say();
		
		//抽象工厂
		Factory lenovoFactory = new LenovoFactory();
		Computer computer = lenovoFactory.createComputer();
		Mouse mouse = lenovoFactory.createMouse();
		Keyboard keyboard = lenovoFactory.createKeyboard();
		computer.say();
		mouse.say();
		keyboard.say();
	}

}
