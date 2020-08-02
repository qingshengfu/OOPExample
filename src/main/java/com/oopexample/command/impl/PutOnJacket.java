package com.oopexample.command.impl;

import com.oopexample.CommandConstants;
import com.oopexample.CommandEvent;
import com.oopexample.rules.impl.Rules;

public class PutOnJacket extends Command {

	public PutOnJacket(int id, String name) {
		super(id, name);
		predicate = Rules.checkTaskDone.apply(CommandConstants.PAJAMA_OFF_COMM)
		        .and(Rules.OnlyOneCloth.apply(CommandConstants.JACKET_COMM))
		        .and(Rules.NoError.apply(CommandConstants.JACKET_COMM))
		        .and(Rules.checkTaskDone.apply(CommandConstants.SHIRT_COMM))
		        .and(Rules.IsCold);

	}

	@Override
	protected CommandEvent handle(CommandEvent input) {
		return input.addResponse("jacket");
	} 
	

}
