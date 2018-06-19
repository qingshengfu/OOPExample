package com.oopexample.command.impl;

import com.oopexample.CommandEvent;
import com.oopexample.DressWizard;
import com.oopexample.Temperature;
import com.oopexample.command.CommandI;
import com.oopexample.validator.Validator;

public class PutOnPants extends Command implements CommandI {

	public PutOnPants(int id, String name, Validator validator) {
		super(id, name, validator);
		
	}

	@Override
	public CommandEvent action(Temperature temperature) {
		if ( checkPrecondition(temperature, this)) {
			if ( temperature == Temperature.HOT) {
				return new CommandEvent(this.getId(), "shorts");
			} else {
				return new CommandEvent(this.getId(), "pants");
			}
		} else {
			return new CommandEvent(DressWizard.FAIL_COMM, "fail");
		}	
		
	}

}
