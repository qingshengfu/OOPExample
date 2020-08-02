package com.oopexample.command.impl;

import java.util.function.Function;
import java.util.function.Predicate;

import com.oopexample.CommandEvent;

public abstract class Command{
	
	protected Predicate<CommandEvent> predicate;
	
	protected int id;
	protected String name;
	
	public Command(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	abstract protected CommandEvent handle(CommandEvent event);
	
	public CommandEvent apply(CommandEvent event) {
		if(predicate.test(event)) {
			event = handle(event);
		}
		else if(!event.isError()){
			event.setError(true);
			event.addResponse("fail");
		}
		event.addFinishedCommand(this.getId());
		return event;
	}
	
	public Function<CommandEvent, CommandEvent> get() {
		return x -> this.apply(x);
	}

}
