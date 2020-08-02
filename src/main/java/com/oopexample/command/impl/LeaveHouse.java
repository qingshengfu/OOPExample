package com.oopexample.command.impl;

import com.oopexample.CommandConstants;
import com.oopexample.CommandEvent;
import com.oopexample.rules.impl.Rules;

public class LeaveHouse extends Command {

	public LeaveHouse(int id, String name) {
		super(id, name);
		/**  if it is hot, you do not need wear jacket and sock, otherwise you need **/
		predicate = (Rules.IsHot
				.and(Rules.checkTaskDone.apply(CommandConstants.PAJAMA_OFF_COMM))
				.and(Rules.checkTaskDone.apply(CommandConstants.FOOT_WEAR_COMM))
				.and(Rules.checkTaskDone.apply(CommandConstants.HEAD_WEAR_COMM))
				.and(Rules.checkTaskDone.apply(CommandConstants.PANTS_ON_COMM))
				.and(Rules.checkTaskDone.apply(CommandConstants.SHIRT_COMM))
		        .and(Rules.OnlyOneCloth.apply(CommandConstants.LEAVEHOUSE_COMM))
		        .and(Rules.NoError.apply(CommandConstants.LEAVEHOUSE_COMM)))
			.or(Rules.IsCold
				.and(Rules.checkTaskDone.apply(CommandConstants.PAJAMA_OFF_COMM))
				.and(Rules.checkTaskDone.apply(CommandConstants.FOOT_WEAR_COMM))
				.and(Rules.checkTaskDone.apply(CommandConstants.HEAD_WEAR_COMM))
				.and(Rules.checkTaskDone.apply(CommandConstants.JACKET_COMM))
				.and(Rules.checkTaskDone.apply(CommandConstants.PANTS_ON_COMM))
				.and(Rules.checkTaskDone.apply(CommandConstants.SHIRT_COMM))
				.and(Rules.checkTaskDone.apply(CommandConstants.SOCK_COMM))
		        .and(Rules.OnlyOneCloth.apply(CommandConstants.LEAVEHOUSE_COMM))
		        .and(Rules.NoError.apply(CommandConstants.LEAVEHOUSE_COMM)));
		        
	}

	@Override
	protected CommandEvent handle(CommandEvent input) {
		return input.addResponse("leaving house");
	}

}
