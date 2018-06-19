package com.oopexample.command.impl;

import com.oopexample.CommandEvent;
import com.oopexample.DressWizard;
import com.oopexample.Temperature;
import com.oopexample.validator.Validator;

public class FailCommand extends Command {

	public FailCommand(int id, String name, Validator validator) {
		super(id, name, validator);
	}

	@Override
	public CommandEvent action(Temperature temperature) {

		return new CommandEvent(DressWizard.FAIL_COMM, "fail");
	}

}
