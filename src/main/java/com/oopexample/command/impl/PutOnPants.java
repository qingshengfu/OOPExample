package com.oopexample.command.impl;

import com.oopexample.CommandConstants;
import com.oopexample.CommandEvent;
import com.oopexample.Temperature;
import com.oopexample.rules.impl.Rules;

public class PutOnPants extends Command {

	public PutOnPants(int id, String name) {
		super(id, name);
		predicate = Rules.checkTaskDone.apply(CommandConstants.PAJAMA_OFF_COMM)
		        .and(Rules.OnlyOneCloth.apply(CommandConstants.PANTS_ON_COMM))
		        .and(Rules.NoError.apply(CommandConstants.PANTS_ON_COMM));
		
	}

	@Override
	protected CommandEvent handle(CommandEvent input) {
		if ( Temperature.HOT == input.getTemp()) {
			return input.addResponse("shorts");
		} else {
			return input.addResponse("pants");
		}
	}


}
