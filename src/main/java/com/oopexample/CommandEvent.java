package com.oopexample;

public class CommandEvent {
	private Integer commandId;
	private String response;
	private boolean status;
	
	public CommandEvent() {
		
	}

	public CommandEvent(int commandId, String response, boolean status) {
		super();
		this.commandId = commandId;
		this.response = response;
		this.status = status;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	

	public Integer getCommandId() {
		return commandId;
	}

	public void setCommandId(int commandId) {
		this.commandId = commandId;
	}

	@Override
	public int hashCode() {
		return getCommandId().hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		CommandEvent other = (CommandEvent) obj;
		if (this == other) {
			return true;
		}

		if (other == null || other.getClass() != other.getClass()) {
			return false;
		}

		return (other.getCommandId().equals(this.getCommandId()));
	}

	public boolean getStatus() {
		// TODO Auto-generated method stub
		return status;
	}
}
