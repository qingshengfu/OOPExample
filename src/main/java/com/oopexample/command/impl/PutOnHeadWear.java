package com.oopexample.command.impl;

import com.oopexample.CommandEvent;
import com.oopexample.DressWizard;
import com.oopexample.Temperature;
import com.oopexample.command.CommandI;

public class PutOnHeadWear extends Command implements CommandI{

	public PutOnHeadWear(int id, String name) {
		super(id, name);
	}

	@Override
	public CommandEvent action(DressWizard context) {
		if ( checkPrecondition(context)) {
			if ( Temperature.HOT == context.getTemp()) {
				return new CommandEvent(this.getId(), "sunglasses", true);
			} else {
				return new CommandEvent(this.getId(), "hat", true);
			}
		} else {
			return new CommandEvent(DressWizard.FAIL_COMM, "fail", false);
		}	
		
	}

}
