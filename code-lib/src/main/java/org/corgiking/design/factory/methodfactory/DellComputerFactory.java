package org.corgiking.design.factory.methodfactory;

import org.corgiking.design.factory.DellComputer;

public class DellComputerFactory implements ComputerFactory {

	@Override
	public DellComputer create() {
		return new DellComputer();
	}

}
