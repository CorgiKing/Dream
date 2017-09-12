package org.corgiking.design.factory.abstractfactory;

import org.corgiking.design.factory.product.Computer;
import org.corgiking.design.factory.product.Keyboard;
import org.corgiking.design.factory.product.Mouse;

public interface Factory {
	Computer createComputer();
	Mouse createMouse();
	Keyboard createKeyboard();
}
