package com.oopexample.rules;

import com.oopexample.DressWizard;
import com.oopexample.Temperature;
import com.oopexample.command.impl.Command;

public interface Rule {
	
	public boolean valid(DressWizard context, Temperature temp, Command command);

}
