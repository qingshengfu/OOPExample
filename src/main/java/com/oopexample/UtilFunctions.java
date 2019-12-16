package com.oopexample;

import java.util.List;

import com.oopexample.command.impl.Command;

public class UtilFunctions {

	public void commands(List<Command> commands, DressWizard context) {
		commands.stream().map(cmd -> cmd.action(context))
		                 .map(event -> context.addWorkDone(event))
		                 .anyMatch(t -> !t); 
		               
	}
}
