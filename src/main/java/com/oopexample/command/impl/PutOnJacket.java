package com.oopexample.command.impl;

import com.oopexample.CommandEvent;
import com.oopexample.DressWizard;
import com.oopexample.Temperature;
import com.oopexample.command.CommandI;
import com.oopexample.validator.Validator;

public class PutOnJacket extends Command implements CommandI{

	public PutOnJacket(int id, String name, Validator validator) {
		super(id, name, validator);
		
	}

	@Override
	public CommandEvent action(Temperature temperature) {
		if ( checkPrecondition(temperature, this)) {
			return new CommandEvent(this.getId(), "jacket");
		} else {
			return new CommandEvent(DressWizard.FAIL_COMM, "fail");
		}	
		
	}

}
