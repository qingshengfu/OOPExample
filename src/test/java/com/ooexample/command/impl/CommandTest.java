package com.ooexample.command.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.oopexample.DressWizard;
import com.oopexample.CommandEvent;
import com.oopexample.Temperature;
import com.oopexample.command.impl.Command;

public class CommandTest {
	
	DressWizard wizard; 
	List<Integer> commands;
	
	@Before
	public void setup() {
		wizard = new DressWizard();
		wizard.initData();
		commands = new ArrayList<>();
	}
	
	@Test
	public void shoudlDoneWearingSock() {
		
		commands.add(DressWizard.PAJAMA_OFF_COMM);
		commands.add(DressWizard.SHIRT_COMM);
		commands.add(DressWizard.HEAD_WEAR_COMM);
		commands.add(DressWizard.SOCK_COMM);
		wizard.runWizard(Temperature.COLD, commands);
		Assert.assertFalse(wizard.containsToDo(DressWizard.SOCK_COMM));
		
	}
	
	@Test
	public void shoudlNotBeAbleToWearSock() {
		commands.add(DressWizard.PAJAMA_OFF_COMM);
		commands.add(DressWizard.SHIRT_COMM);
		commands.add(DressWizard.HEAD_WEAR_COMM);
		commands.add(DressWizard.SOCK_COMM);
		
		wizard.runWizard(Temperature.HOT, commands);
		Assert.assertTrue(wizard.containsToDo(DressWizard.SOCK_COMM));
		Assert.assertTrue(wizard.isFaied());
		
	}
	
	@Test
	public void shoudlDoneWearingJacket() {
		commands.add(DressWizard.PAJAMA_OFF_COMM);
		commands.add(DressWizard.SHIRT_COMM);
		commands.add(DressWizard.HEAD_WEAR_COMM);
		commands.add(DressWizard.JACKET_COMM);
		wizard.runWizard(Temperature.COLD, commands);
		Assert.assertFalse(wizard.containsToDo(DressWizard.JACKET_COMM));
		
	}
	
	@Test
	public void shoudlNotBeAbleToWearJacket() {
		commands.add(DressWizard.PAJAMA_OFF_COMM);
		commands.add(DressWizard.SHIRT_COMM);
		commands.add(DressWizard.HEAD_WEAR_COMM);
		commands.add(DressWizard.JACKET_COMM);
		wizard.runWizard(Temperature.HOT, commands);
		Assert.assertTrue(wizard.containsToDo(DressWizard.JACKET_COMM));
		Assert.assertTrue(wizard.isFaied());
		
	}
	
	@Test
	public void shoudlDoneWearingFootWareCold() {
		commands.add(DressWizard.PAJAMA_OFF_COMM);
		commands.add(DressWizard.SHIRT_COMM);
		commands.add(DressWizard.HEAD_WEAR_COMM);
		commands.add(DressWizard.PANTS_ON_COMM);
		commands.add(DressWizard.SOCK_COMM);
		commands.add(DressWizard.FOORT_WEAR_COMM);
		wizard.runWizard(Temperature.COLD, commands);
		
		Assert.assertFalse(wizard.containsToDo(DressWizard.FOORT_WEAR_COMM));
		Assert.assertTrue(wizard.containsEvent(new CommandEvent(DressWizard.FOORT_WEAR_COMM, "boots")));
		
	}
	
	@Test
	public void shoudlDoneWearingFootWareHOT() {
		commands.add(DressWizard.PAJAMA_OFF_COMM);
		commands.add(DressWizard.SHIRT_COMM);
		commands.add(DressWizard.HEAD_WEAR_COMM);
		commands.add(DressWizard.PANTS_ON_COMM);
		commands.add(DressWizard.FOORT_WEAR_COMM);
		wizard.runWizard(Temperature.HOT, commands);
		
		Assert.assertFalse(wizard.containsToDo(DressWizard.FOORT_WEAR_COMM));
		Assert.assertTrue(wizard.containsEvent(new CommandEvent(DressWizard.FOORT_WEAR_COMM, "sandals")));
		
	}
	
	@Test
	public void shoudlNotBeAbleToWearFootWare() {
		commands.add(DressWizard.PAJAMA_OFF_COMM);
		commands.add(DressWizard.SHIRT_COMM);
		commands.add(DressWizard.HEAD_WEAR_COMM);
		commands.add(DressWizard.JACKET_COMM);
		commands.add(DressWizard.FOORT_WEAR_COMM);
		wizard.runWizard(Temperature.HOT, commands);
		
		Assert.assertTrue(wizard.containsToDo(DressWizard.FOORT_WEAR_COMM));
		Assert.assertTrue(wizard.isFaied());
		
	}
	
	@Test
	public void shoudlDoneTakingOffPjs() {
		commands.add(DressWizard.PAJAMA_OFF_COMM);
		wizard.runWizard(Temperature.HOT, commands);
		
		Assert.assertFalse(wizard.containsToDo(DressWizard.PAJAMA_OFF_COMM));
		Assert.assertTrue(wizard.containsEvent(new CommandEvent(DressWizard.PAJAMA_OFF_COMM, "Removing PJs")));
		
	}
	
	@Test
	public void shouldFailOnInvalidCommand() {
		commands.add(100);
		wizard.runWizard(Temperature.HOT, commands);
		
		Assert.assertTrue ( wizard.isFaied());
		Assert.assertTrue (wizard.containsEvent(new CommandEvent(DressWizard.FAIL_COMM, "Fail")));
	}

}
