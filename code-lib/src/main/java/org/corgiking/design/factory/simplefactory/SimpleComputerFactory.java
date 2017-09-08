package org.corgiking.design.factory.simplefactory;

import org.corgiking.design.factory.Computer;
import org.corgiking.design.factory.DellComputer;
import org.corgiking.design.factory.LenovoComputer;

public class SimpleComputerFactory {
	
	public static Computer create(String type){
		if (type.trim().equals("dell")) {
			return new DellComputer();
		}else if (type.trim().equals("lenovo")) {
			return new LenovoComputer();
		}
		return null;
	}
	
}
