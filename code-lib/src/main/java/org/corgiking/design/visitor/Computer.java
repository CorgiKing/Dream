package org.corgiking.design.visitor;

public class Computer implements ComputerPart {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
