package com.oopexample.validator.impl;

import java.util.ArrayList;
import java.util.List;

import com.oopexample.DressWizard;
import com.oopexample.Temperature;
import com.oopexample.command.impl.Command;
import com.oopexample.rules.Rule;
import com.oopexample.validator.Validator;

public class ValidatorImpl implements Validator{
	
	private List<Rule> rules;
	private final DressWizard context;
	
	public ValidatorImpl(DressWizard context) {
		this.rules = new ArrayList<>();
		this.context = context;
	}
	
	public ValidatorImpl(DressWizard context, Rule...rulesArr) {
		this.context = context;
		this.rules = new ArrayList<>();
		for ( Rule rule : rulesArr) {
			this.rules.add(rule);
		}
		
	}
	
	public ValidatorImpl(DressWizard context, List<Rule> rules) {
		super();
		this.rules = rules;
		this.context = context;
	}
	
	public boolean valid(final Temperature temp, final Command command ) {
		return rules.stream().map( rule -> rule.valid(context, temp, command ))
				             .reduce(true, (a , b) -> a && b);
	}

	@Override
	public void addRule(Rule rule) {
		this.rules.add(rule);
		
	}

}
