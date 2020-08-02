package com.oopexample.command.impl;

import com.oopexample.CommandEvent;

public class NoneExistingCommand extends Command {

	public NoneExistingCommand(int id, String name) {
		super(id, name);
		predicate = x -> false; 
	}

	@Override
	protected CommandEvent handle(CommandEvent input) {
		return input.addResponse("fail");
	}

}
