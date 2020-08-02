package com.oopexample.command.impl;

import com.oopexample.CommandConstants;
import com.oopexample.CommandEvent;
import com.oopexample.rules.impl.Rules;

public class PutOnShirt extends Command {

	public PutOnShirt(int id, String name) {
		super(id, name);
		predicate = Rules.checkTaskDone.apply(CommandConstants.PAJAMA_OFF_COMM)
		        .and(Rules.OnlyOneCloth.apply(CommandConstants.SHIRT_COMM))
		        .and(Rules.NoError.apply(CommandConstants.SHIRT_COMM));
	}

	@Override
	protected CommandEvent handle(CommandEvent input) {
		return input.addResponse("shirts");
	}

}
