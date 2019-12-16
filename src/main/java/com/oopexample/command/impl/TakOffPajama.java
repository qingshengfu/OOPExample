package com.oopexample.command.impl;

import com.oopexample.CommandEvent;
import com.oopexample.DressWizard;
import com.oopexample.command.CommandI;

public class TakOffPajama extends Command implements CommandI {

	public TakOffPajama(int id, String name) {
		super(id, name);
	}

	@Override
	public CommandEvent action(DressWizard context) {
		if ( checkPrecondition(context)) {
			return new CommandEvent(this.getId(), "Removing PJs", true);
		} else {
			return new CommandEvent(DressWizard.FAIL_COMM, "fail", false);
		}	
		
	}

}
