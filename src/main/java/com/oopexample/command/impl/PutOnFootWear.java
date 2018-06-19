package com.oopexample.command.impl;

import com.oopexample.CommandEvent;
import com.oopexample.DressWizard;
import com.oopexample.Temperature;
import com.oopexample.command.CommandI;
import com.oopexample.validator.Validator;

public class PutOnFootWear extends Command implements CommandI{

	public PutOnFootWear(int id, String name, Validator validator) {
		super(id, name, validator);
	}

	@Override
	public CommandEvent action(Temperature temperature) {
		if ( checkPrecondition( temperature, this )) {
			CommandEvent res = null;
			if ( temperature == Temperature.HOT) {
				res = new CommandEvent(this.getId(), "sandals");
				
			} else if ( temperature == Temperature.COLD ) {
				res = new CommandEvent(this.getId(), "boots");
			}
			
			return res;
		} else {
			return new CommandEvent(DressWizard.FAIL_COMM, "fail");
		}	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
