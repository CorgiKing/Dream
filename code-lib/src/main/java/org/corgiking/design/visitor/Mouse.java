package org.corgiking.design.visitor;

public class Mouse implements ComputerPart {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
