package com.oopexample.command;

import com.oopexample.CommandEvent;
import com.oopexample.Temperature;

public interface CommandI {
	
	public CommandEvent action(Temperature temperature);
	
}
