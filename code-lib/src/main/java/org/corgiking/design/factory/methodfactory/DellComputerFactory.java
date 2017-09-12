package org.corgiking.design.factory.methodfactory;

import org.corgiking.design.factory.product.DellComputer;

public class DellComputerFactory implements ComputerFactory {

	@Override
	public DellComputer create() {
		return new DellComputer();
	}

}
