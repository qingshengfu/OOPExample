package com.oopexample.command.impl;

import java.util.Arrays;
import java.util.List;

import com.oopexample.CommandEvent;
import com.oopexample.DressWizard;
import com.oopexample.command.CommandI;
import com.oopexample.rules.Rule;

public abstract class Command implements CommandI{
	
	protected int id;
	protected String name;
	protected List<Rule> rules;;
	
	public Command(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		
	}
	
	@Override
	abstract public CommandEvent action(DressWizard context);
	

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
	
	public Command addRules(Rule...rule) {
		rules.addAll(Arrays.asList(rule));
		return this;
	}
	
	protected boolean checkPrecondition(DressWizard context ) {
		return rules.stream().map( rule -> rule.valid(context, this ))
	             .reduce(true, (a , b) -> a && b);
	}
	
	

}
