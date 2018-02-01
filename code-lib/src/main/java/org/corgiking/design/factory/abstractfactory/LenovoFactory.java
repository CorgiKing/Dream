package org.corgiking.design.factory.abstractfactory;

import org.corgiking.design.factory.product.Keyboard;
import org.corgiking.design.factory.product.LenovoComputer;
import org.corgiking.design.factory.product.LenovoKeyboard;
import org.corgiking.design.factory.product.LenovoMouse;
import org.corgiking.design.factory.product.Mouse;

public class LenovoFactory implements Factory {

	@Override
	public LenovoComputer createComputer() {
		return new LenovoComputer();
	}

	@Override
	public Mouse createMouse() {
		return new LenovoMouse();
	}

	@Override
	public Keyboard createKeyboard() {
		return new LenovoKeyboard();
	}

}
