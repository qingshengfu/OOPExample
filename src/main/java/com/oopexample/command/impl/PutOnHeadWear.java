package com.oopexample.command.impl;

import com.oopexample.CommandEvent;
import com.oopexample.DressWizard;
import com.oopexample.Temperature;
import com.oopexample.command.CommandI;
import com.oopexample.validator.Validator;

public class PutOnHeadWear extends Command implements CommandI{

	public PutOnHeadWear(int id, String name, Validator validator) {
		super(id, name, validator);
	}

	@Override
	public CommandEvent action(Temperature temperature) {
		if ( checkPrecondition(temperature, this)) {
			if ( Temperature.HOT == temperature) {
				return new CommandEvent(this.getId(), "sunglasses");
			} else {
				return new CommandEvent(this.getId(), "hat");
			}
		} else {
			return new CommandEvent(DressWizard.FAIL_COMM, "fail");
		}	
		
	}

}
