package com.oopexample.command.impl;

import com.oopexample.CommandConstants;
import com.oopexample.CommandEvent;
import com.oopexample.rules.impl.Rules;

public class PutOnSock extends Command {

	public PutOnSock(int id, String name) {
		super(id, name);
		predicate = Rules.checkTaskDone.apply(CommandConstants.PAJAMA_OFF_COMM)
		        .and(Rules.OnlyOneCloth.apply(CommandConstants.SOCK_COMM))
		        .and(Rules.NoError.apply(CommandConstants.SOCK_COMM))
		        .and(Rules.IsCold);
	}

	@Override
	protected CommandEvent handle(CommandEvent input) {
		return input.addResponse("socks");
	} 

}
