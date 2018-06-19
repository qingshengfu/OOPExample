package com.oopexample.rules.impl;

import com.oopexample.DressWizard;
import com.oopexample.Temperature;
import com.oopexample.command.impl.Command;
import com.oopexample.rules.Rule;

public class LeaveHouseRule implements Rule{



	@Override
	public boolean valid(DressWizard context, Temperature temp, Command command) {
		return context.isFinished( temp );
		
	}

}
