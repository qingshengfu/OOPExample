package com.oopexample.command.impl;

import com.oopexample.CommandEvent;
import com.oopexample.DressWizard;
import com.oopexample.Temperature;
import com.oopexample.command.CommandI;

public class PutOnPants extends Command implements CommandI {

	public PutOnPants(int id, String name) {
		super(id, name);
		
	}

	@Override
	public CommandEvent action(DressWizard context) {
		if ( checkPrecondition(context)) {
			if ( context.getTemp() == Temperature.HOT) {
				return new CommandEvent(this.getId(), "shorts", true);
			} else {
				return new CommandEvent(this.getId(), "pants", true);
			}
		} else {
			return new CommandEvent(DressWizard.FAIL_COMM, "fail", false);
		}	
		
	}

}
