package com.oopexample.command.impl;

import com.oopexample.CommandEvent;
import com.oopexample.DressWizard;
import com.oopexample.Temperature;
import com.oopexample.command.CommandI;

public class PutOnFootWear extends Command implements CommandI{

	public PutOnFootWear(int id, String name) {
		super(id, name);
	}

	@Override
	public CommandEvent action(DressWizard context) {
		if ( checkPrecondition( context )) {
			CommandEvent res = null;
			if ( context.getTemp() == Temperature.HOT) {
				res = new CommandEvent(this.getId(), "sandals", true);
				
			} else if ( context.getTemp() == Temperature.COLD ) {
				res = new CommandEvent(this.getId(), "boots", true);
			}
			
			return res;
		} else {
			return new CommandEvent(DressWizard.FAIL_COMM, "fail", false);
		}	
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
