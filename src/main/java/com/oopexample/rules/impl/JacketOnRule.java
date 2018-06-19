package com.oopexample.rules.impl;

import com.oopexample.DressWizard;
import com.oopexample.Temperature;
import com.oopexample.command.impl.Command;
import com.oopexample.rules.Rule;

public class JacketOnRule implements Rule{

	

	@Override
	public boolean valid(DressWizard context, Temperature temp, Command command) {
		if ( temp == Temperature.COLD) {
			return context.isTaskDone( DressWizard.JACKET_COMM);
		} else {
			return true;
		}
		
	}

}
