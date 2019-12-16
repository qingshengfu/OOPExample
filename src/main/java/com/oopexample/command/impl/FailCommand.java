package com.oopexample.command.impl;

import com.oopexample.CommandEvent;
import com.oopexample.DressWizard;

public class FailCommand extends Command {

	public FailCommand(int id, String name) {
		super(id, name);
	}

	@Override
	public CommandEvent action(DressWizard context) {

		return new CommandEvent(DressWizard.FAIL_COMM, "fail", false);
	}

}
