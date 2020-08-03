package com.oopexample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandEvent {
	private Set<Integer> finishedCommandIds;
	private List<String> responses;
	private Temperature temp;
	private boolean error;
	
	public CommandEvent(Temperature temp) {
		finishedCommandIds = new HashSet<>();
		responses = new ArrayList<>();
		this.temp = temp;
	}

	public boolean isError() {
		return error;
	}

	public CommandEvent setError(boolean status) {
		this.error = status;
		return this;
	}

	public CommandEvent addResponse(String response) {
		responses.add(response);
		return this;
	}
	
	public CommandEvent addFinishedCommand(Integer commandId) {
		finishedCommandIds.add(commandId);
		return this;
	}
	
	public boolean isCommandFinished(Integer commandId) {
		return finishedCommandIds.contains(commandId);
	}

	public Temperature getTemp() {
		return temp;
	}

	public void setTemp(Temperature temp) {
		this.temp = temp;
	}
	
	public String printResponse() {
		return responses.stream().collect(Collectors.joining(", "));
	}
	
}
