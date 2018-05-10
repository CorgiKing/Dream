package org.corgiking.design.visitor;

public class OneVisitor implements Visitor {

	@Override
	public void visit(Computer computer) {
		System.out.println("访问了 电脑");
	}

	@Override
	public void visit(Mouse mouse) {
		System.out.println("访问了 鼠标");
	}

	@Override
	public void visit(Keyboard keyboard) {
		System.out.println("访问了 键盘");
	}

	@Override
	public void visit(Monitor monitor) {
		System.out.println("访问了 显示器");
	}

}
