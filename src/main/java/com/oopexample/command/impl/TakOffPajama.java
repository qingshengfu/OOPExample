package com.oopexample.command.impl;

import com.oopexample.CommandConstants;
import com.oopexample.CommandEvent;
import com.oopexample.rules.impl.Rules;

public class TakOffPajama extends Command{

	public TakOffPajama(int id, String name) {
		super(id, name);
		predicate = Rules.OnlyOneCloth.apply(CommandConstants.PAJAMA_OFF_COMM)
		        .and(Rules.NoError.apply(CommandConstants.PAJAMA_OFF_COMM));
	}

	@Override
	protected CommandEvent handle(CommandEvent input) {
		return input.addResponse("Removing PJs");
	}

}
