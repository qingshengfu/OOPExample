package com.oopexample.command.impl;

import com.oopexample.CommandConstants;
import com.oopexample.CommandEvent;
import com.oopexample.Temperature;
import com.oopexample.rules.impl.Rules;

public class PutOnFootWear extends Command{

	public PutOnFootWear(int id, String name) {
		super(id, name);
		predicate = Rules.checkTaskDone.apply(CommandConstants.PAJAMA_OFF_COMM)
		        .and(Rules.OnlyOneCloth.apply(CommandConstants.FOOT_WEAR_COMM))
		        .and(Rules.NoError.apply(CommandConstants.FOOT_WEAR_COMM))
		        .and( Rules.IsHot.or(
		        		Rules.IsCold.and(Rules.checkTaskDone.apply(CommandConstants.SOCK_COMM))))
		        .and(Rules.checkTaskDone.apply(CommandConstants.PANTS_ON_COMM));
	}

	@Override
	protected CommandEvent handle(CommandEvent input) {
		if ( Temperature.HOT == input.getTemp()) {
			return input.addResponse("sandals");
		} else {
			return input.addResponse("boots");
		}
	}

}
