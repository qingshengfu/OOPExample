package com.oopexample.rules.impl;

import java.util.function.Function;
import java.util.function.Predicate;

import com.oopexample.CommandEvent;
import com.oopexample.Temperature;

public class Rules {
	
	public static Function<Integer, Predicate<CommandEvent>> checkTaskDone = c -> {
		return e -> e.isCommandFinished(c);
	};
	
	public static Function<Integer, Predicate<CommandEvent>> OnlyOneCloth = c -> {
		return checkTaskDone.apply(c).negate();
	};
	
	public static Function<Integer, Predicate<CommandEvent>> NoError = c -> {
		return e -> !e.isError();
	};
	
	public static Predicate<CommandEvent> IsHot = c -> {
		return c.getTemp() == Temperature.HOT;
	};
	
	public static Predicate<CommandEvent> IsCold = IsHot.negate();
}
