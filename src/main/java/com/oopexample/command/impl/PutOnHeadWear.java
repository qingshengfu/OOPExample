package com.oopexample.command.impl;

import com.oopexample.CommandConstants;
import com.oopexample.CommandEvent;
import com.oopexample.Temperature;
import com.oopexample.rules.impl.Rules;

public class PutOnHeadWear extends Command {
	
	public PutOnHeadWear(int id, String name) {
		super(id, name);
		predicate = Rules.checkTaskDone.apply(CommandConstants.PAJAMA_OFF_COMM)
				        .and(Rules.OnlyOneCloth.apply(CommandConstants.HEAD_WEAR_COMM))
				        .and(Rules.checkTaskDone.apply(CommandConstants.SHIRT_COMM))
				        .and(Rules.NoError.apply(CommandConstants.HEAD_WEAR_COMM));
	}

	@Override
	protected CommandEvent handle(CommandEvent input) {
		if ( Temperature.HOT == input.getTemp()) {
			return input.addResponse("sunglasses");
		} else {
			return input.addResponse("hat");
		}
	}

	

}
