package com.oopexample;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.oopexample.command.impl.Command;
import com.oopexample.command.impl.LeaveHouse;
import com.oopexample.command.impl.NoneExistingCommand;
import com.oopexample.command.impl.PutOnFootWear;
import com.oopexample.command.impl.PutOnHeadWear;
import com.oopexample.command.impl.PutOnJacket;
import com.oopexample.command.impl.PutOnPants;
import com.oopexample.command.impl.PutOnShirt;
import com.oopexample.command.impl.PutOnSock;
import com.oopexample.command.impl.TakOffPajama;

/**
 * 
 * @author andy
 *
 */
public class DressWizard {
   // not thread safe
	private Map<Integer, Command> availableCommands;
	private Command badCommand = new NoneExistingCommand(CommandConstants.FAIL_COMM, "Bad Command");

	public DressWizard() {
		List<Command> commands = Arrays.asList(
			new PutOnFootWear(CommandConstants.FOOT_WEAR_COMM, "Put on foot ware"),
			new PutOnHeadWear(CommandConstants.HEAD_WEAR_COMM, "Put on headwear"),
			new PutOnSock(CommandConstants.SOCK_COMM, "Put on sock"),
			new PutOnShirt(CommandConstants.SHIRT_COMM, "Put on shirt"),
			new PutOnJacket(CommandConstants.JACKET_COMM, "Put on jack"),
			new PutOnPants(CommandConstants.PANTS_ON_COMM, "Put on pants"),
			new LeaveHouse(CommandConstants.LEAVEHOUSE_COMM, "Leaving House"),
			new TakOffPajama(CommandConstants.PAJAMA_OFF_COMM, "Take off pajamas"));
		/*
		 * put commands into map
		 */
		availableCommands = commands.stream()
				.collect(Collectors.toMap(Command::getId, Function.identity()));
	}

	public String runWizard(Temperature temperature, List<Integer> commands) {
		CommandEvent event = new CommandEvent(temperature);
		Function<CommandEvent, CommandEvent> composed = commands.stream()
				.map(i -> availableCommands.getOrDefault(i, badCommand))
				.map(Command::get)
				.reduce((x, y) -> x.andThen(y))
				.orElse(badCommand.get());
		
		return composed.apply(event).printResponse();
		
	}


}
