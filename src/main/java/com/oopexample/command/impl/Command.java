package com.oopexample.command.impl;

import com.oopexample.CommandEvent;
import com.oopexample.DressWizard;
import com.oopexample.Temperature;
import com.oopexample.command.CommandI;
import com.oopexample.validator.Validator;

public abstract class Command implements CommandI{
	
	protected int id;
	protected String name;
	protected Validator validator;
	
	public Command(int id, String name, Validator validator) {
		super();
		this.id = id;
		this.name = name;
		this.validator = validator;
		
	}
	
	abstract public CommandEvent action(Temperature temperature);
	
	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
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
	
	protected boolean checkPrecondition(Temperature temp, Command command ) {
		return validator.valid(temp, command);
	}
	
	

}
