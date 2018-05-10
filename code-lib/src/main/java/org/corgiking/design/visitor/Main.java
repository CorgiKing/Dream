package org.corgiking.design.visitor;

public class Main {

	public static void main(String[] args) {
		ComputerPart part = new Keyboard();
		Visitor visitor = new OneVisitor();
		part.accept(visitor);
	}

}
