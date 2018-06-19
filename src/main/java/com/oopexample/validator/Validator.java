package com.oopexample.validator;

import com.oopexample.Temperature;
import com.oopexample.command.impl.Command;
import com.oopexample.rules.Rule;

public interface Validator {
	
	public boolean valid(Temperature temperature, Command command);
	public void addRule(Rule rule);

}
