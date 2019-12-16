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

/**
 * 
 * @author andy
 *
 */
public class DressWizard {
   // not thread safe
	private Map<Integer, Command> availableCommands;
	private Set<Integer> toDos;
	private List<CommandEvent> doneList;
	private Temperature temp;;

	public DressWizard() {
		availableCommands = new HashMap<>();
		toDos = new HashSet<>();
		doneList = new ArrayList<>();
	}

	public void initData() {
		Rule jacketHotRule = new JacketHotRule();
		Rule leaveHouseRule = new LeaveHouseRule();
		Rule onlyOneClothRule = new OnlyOneClothingRule();
		Rule pajamaOffRule = new PajamaOffRule();
		Rule pantOnRule = new PantsOnRule();
		Rule shirtOnRule = new ShirtOnRule();
		Rule SockOnRule = new SockOnRule();
		Rule sockHotRule = new SocksHotRule();

		/*
		 * initialize available commands
		 */
		addCommand(new PutOnFootWear(FOORT_WEAR_COMM, "Put on foot ware").addRules(pajamaOffRule, pantOnRule,
				SockOnRule, onlyOneClothRule));
		addCommand(new PutOnHeadWear(HEAD_WEAR_COMM, "Put on headwear").addRules(pajamaOffRule, shirtOnRule,
				onlyOneClothRule));
		addCommand(new PutOnSock(SOCK_COMM, "Put on sock").addRules(pajamaOffRule, sockHotRule,
				onlyOneClothRule));
		addCommand(new PutOnShirt(SHIRT_COMM, "Put on shirt").addRules(pajamaOffRule,
				onlyOneClothRule));
		addCommand(new PutOnJacket(JACKET_COMM, "Put on jack").addRules(pajamaOffRule, shirtOnRule,
				jacketHotRule, onlyOneClothRule));
		addCommand(new PutOnPants(PANTS_ON_COMM, "Put on pants").addRules(pajamaOffRule, onlyOneClothRule));
		addCommand(new LeaveHouse(LEAVEHOUSE_COMM, "Leaving House").addRules(leaveHouseRule, onlyOneClothRule));
		addCommand(new TakOffPajama(PAJAMA_OFF_COMM, "Take off pajamas").addRules(onlyOneClothRule));

		/*
		 * initialize toDo list for dress up
		 */
		availableCommands.values().forEach(command -> toDos.add(command.getId()));
	}

	public boolean isFinished() {
		if (this.temp == Temperature.COLD) {
			return toDos.size() == 1 && toDos.contains(LEAVEHOUSE_COMM);
		} 
		
		return toDos.size() == 3 && toDos.contains(SOCK_COMM) && toDos.contains(JACKET_COMM)
				&& toDos.contains(LEAVEHOUSE_COMM);

	}

	public boolean addWorkDone(CommandEvent event) {
		toDos.remove(event.getCommandId());
		doneList.add(event);
		return event.getStatus();
	}

	public Command getAvailableCommand(int command) {
		Command comm = availableCommands.get(command);
		if (comm == null) {
			return new FailCommand(command, "fail");
		}
		return comm;
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
		this.temp = temperature;
		List<Command> cmds = commands.stream().map(i -> getAvailableCommand(i))
				.collect(Collectors.toList());
		
		cmds.stream().map(cmd -> cmd.action(this))
        	.map(event -> this.addWorkDone(event))
        	.anyMatch(t -> !t);
		
	}
	
	public Temperature getTemp() {
		return this.temp;
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
