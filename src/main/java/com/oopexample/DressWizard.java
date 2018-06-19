package com.oopexample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.oopexample.command.impl.Command;
import com.oopexample.command.impl.FailCommand;
import com.oopexample.command.impl.LeaveHouse;
import com.oopexample.command.impl.PutOnFootWear;
import com.oopexample.command.impl.PutOnHeadWear;
import com.oopexample.command.impl.PutOnJacket;
import com.oopexample.command.impl.PutOnPants;
import com.oopexample.command.impl.PutOnShirt;
import com.oopexample.command.impl.PutOnSock;
import com.oopexample.command.impl.TakOffPajama;
import com.oopexample.rules.Rule;
import com.oopexample.rules.impl.JacketHotRule;
import com.oopexample.rules.impl.LeaveHouseRule;
import com.oopexample.rules.impl.OnlyOneClothingRule;
import com.oopexample.rules.impl.PajamaOffRule;
import com.oopexample.rules.impl.PantsOnRule;
import com.oopexample.rules.impl.ShirtOnRule;
import com.oopexample.rules.impl.SockOnRule;
import com.oopexample.rules.impl.SocksHotRule;
import com.oopexample.rules.impl.ValidCommandRule;
import com.oopexample.validator.Validator;
import com.oopexample.validator.impl.ValidatorImpl;

public class DressWizard {

	private Map<Integer, Command> availableCommands;
	private Set<Integer> toDos;
	private List<CommandEvent> doneList;
	private Command failCommand;
	boolean failed;

	public DressWizard() {
		availableCommands = new HashMap<>();
		toDos = new HashSet<>();
		doneList = new ArrayList<>();
		failCommand = new FailCommand(FAIL_COMM, "Fail", new ValidatorImpl(this));
		failed = false;
	}

	public void initData() {
		/*
		 * initialize rules
		 */
		Rule jacketHotRule = new JacketHotRule();
		Rule leaveHouseRule = new LeaveHouseRule();
		Rule onlyOneClothRule = new OnlyOneClothingRule();
		Rule pajamaOffRule = new PajamaOffRule();
		Rule pantOnRule = new PantsOnRule();
		Rule shirtOnRule = new ShirtOnRule();
		Rule SockOnRule = new SockOnRule();
		Rule sockHotRule = new SocksHotRule();
		Rule validCommandRule = new ValidCommandRule();

		/*
		 * validators
		 */
		Validator putOnFootWearValidator = new ValidatorImpl(this, validCommandRule, pajamaOffRule, pantOnRule,
				SockOnRule, onlyOneClothRule);
		Validator putOnHeadWearValidator = new ValidatorImpl(this, validCommandRule, pajamaOffRule, shirtOnRule,
				onlyOneClothRule);
		Validator putOnSockValidator = new ValidatorImpl(this, validCommandRule, pajamaOffRule, sockHotRule,
				onlyOneClothRule);
		Validator putOnShirtValidator = new ValidatorImpl(this, validCommandRule, validCommandRule, pajamaOffRule,
				onlyOneClothRule);
		Validator putOnJacketValidator = new ValidatorImpl(this, validCommandRule, pajamaOffRule, shirtOnRule,
				jacketHotRule, onlyOneClothRule);
		Validator putOnPantsValidator = new ValidatorImpl(this, validCommandRule, pajamaOffRule, onlyOneClothRule);
		Validator leaveHouseValidator = new ValidatorImpl(this, validCommandRule, leaveHouseRule, onlyOneClothRule);
		Validator takeOffPajamaValidator = new ValidatorImpl(this, validCommandRule, onlyOneClothRule);

		/*
		 * initialize available commands
		 */
		addCommand(new PutOnFootWear(FOORT_WEAR_COMM, "Put on foot ware", putOnFootWearValidator));
		addCommand(new PutOnHeadWear(HEAD_WEAR_COMM, "Put on headwear", putOnHeadWearValidator));
		addCommand(new PutOnSock(SOCK_COMM, "Put on sock", putOnSockValidator));
		addCommand(new PutOnShirt(SHIRT_COMM, "Put on shirt", putOnShirtValidator));
		addCommand(new PutOnJacket(JACKET_COMM, "Put on jack", putOnJacketValidator));
		addCommand(new PutOnPants(PANTS_ON_COMM, "Put on pants", putOnPantsValidator));
		addCommand(new LeaveHouse(LEAVEHOUSE_COMM, "Leaving House", leaveHouseValidator));
		addCommand(new TakOffPajama(PAJAMA_OFF_COMM, "Take off pajamas", takeOffPajamaValidator));

		/*
		 * initialize toDo list for dress up
		 */
		availableCommands.values().forEach(command -> toDos.add(command.getId()));
	}

	public boolean containsToDo(int commandId) {
		return toDos.contains(commandId);
	}

	public boolean containsEvent(CommandEvent name) {
		return doneList.contains(name);
	}

	public boolean isValidCommand(int commandId) {
		return availableCommands.containsKey(commandId);
	}

	public boolean isFinished(Temperature temp) {
		if (temp == Temperature.COLD) {
			return toDos.size() == 1 && toDos.contains(LEAVEHOUSE_COMM);
		} else {
			return toDos.size() == 3 && toDos.contains(SOCK_COMM) && toDos.contains(JACKET_COMM)
					&& toDos.contains(LEAVEHOUSE_COMM);
		}

	}

	public boolean isFaied() {
		return failed;
	}

	public void addWorkDone(CommandEvent event) {
		toDos.remove(event.getCommandId());
		doneList.add(event);
	}

	public void setFailed(boolean failed) {
		this.failed = failed;
	}

	public Command getAvailableCommand(int command) {
		Command comm = availableCommands.get(command);
		if (comm == null) {
			return getFailCommand();
		}
		return comm;
	}

	public Command getFailCommand() {
		return failCommand;
	}

	public boolean isTaskDone(int commandId) {
		return !toDos.contains(commandId);
	}

	public void addCommand(Command command) {
		availableCommands.put(command.getId(), command);
	}

	public String output() {
		return doneList.stream().map(response -> response.getResponse()).collect(Collectors.joining(", "));
	}

	public void runWizard(Temperature temperature, List<Integer> commands) {
		for (Integer command : commands) {
			CommandEvent event = getAvailableCommand(command).action(temperature);
			addWorkDone(event);
			if (event.getCommandId().equals(FAIL_COMM)) {
				setFailed(true);
				break;
			}
		}
	}

	public static int FAIL_COMM = 0;
	public static int FOORT_WEAR_COMM = 1;
	public static int HEAD_WEAR_COMM = 2;
	public static int SOCK_COMM = 3;
	public static int SHIRT_COMM = 4;
	public static int JACKET_COMM = 5;
	public static int PANTS_ON_COMM = 6;
	public static int LEAVEHOUSE_COMM = 7;
	public static int PAJAMA_OFF_COMM = 8;

}
