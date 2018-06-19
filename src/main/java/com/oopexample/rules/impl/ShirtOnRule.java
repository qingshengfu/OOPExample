package com.oopexample.rules.impl;

import com.oopexample.DressWizard;
import com.oopexample.Temperature;
import com.oopexample.command.impl.Command;
import com.oopexample.rules.Rule;

public class ShirtOnRule implements Rule{

	
	@Override
	public boolean valid(DressWizard context, Temperature temp, Command command) {
		// TODO Auto-generated method stub
		return context.isTaskDone(DressWizard.SHIRT_COMM);
	}

}
