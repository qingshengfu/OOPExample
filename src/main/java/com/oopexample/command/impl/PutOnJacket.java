package com.oopexample.command.impl;

import com.oopexample.CommandEvent;
import com.oopexample.DressWizard;
import com.oopexample.command.CommandI;

public class PutOnJacket extends Command implements CommandI{

	public PutOnJacket(int id, String name) {
		super(id, name);
		
	}

	@Override
	public CommandEvent action(DressWizard context) {
		if ( checkPrecondition(context)) {
			return new CommandEvent(this.getId(), "jacket", true);
		} else {
			return new CommandEvent(DressWizard.FAIL_COMM, "fail", false);
		}	
		
	}

}
