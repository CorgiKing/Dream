package org.corgiking.design.factory.abstractfactory;

import org.corgiking.design.factory.product.Computer;
import org.corgiking.design.factory.product.DellComputer;
import org.corgiking.design.factory.product.DellKeyboard;
import org.corgiking.design.factory.product.DellMouse;
import org.corgiking.design.factory.product.Keyboard;
import org.corgiking.design.factory.product.Mouse;

public class DellFactory implements Factory {

	@Override
	public Computer createComputer() {
		return new DellComputer();
	}

	@Override
	public Mouse createMouse() {
		return new DellMouse();
	}

	@Override
	public Keyboard createKeyboard() {
		return new DellKeyboard();
	}

}
