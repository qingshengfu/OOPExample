package com.ooexample.rules.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.oopexample.DressWizard;
import com.oopexample.CommandEvent;
import com.oopexample.Temperature;
import com.oopexample.command.impl.Command;
import com.oopexample.rules.Rule;
import com.oopexample.rules.impl.HeadWearRule;
import com.oopexample.rules.impl.JacketHotRule;
import com.oopexample.rules.impl.JacketOnRule;
import com.oopexample.rules.impl.LeaveHouseRule;
import com.oopexample.rules.impl.OnlyOneClothingRule;
import com.oopexample.rules.impl.SockOnRule;
import com.oopexample.rules.impl.SocksHotRule;

public class RuleTest {
	
	DressWizard wizard; 
	@Before
	public void setup() {
		wizard = new DressWizard();
		wizard.initData();
	}
	
	@Test
	public void shouldBeInvalidIfJacketOnHot() {
		Rule jacketOnHot = new JacketHotRule();
		Command putonJacket = wizard.getAvailableCommand(DressWizard.JACKET_COMM);
		boolean result = jacketOnHot.valid(wizard, Temperature.HOT, putonJacket);
		Assert.assertFalse ( result);
	}
	
	@Test
	public void shouldBeValidIfJacketOnCold() {
		Rule jacketOnHot = new JacketHotRule();
		Command putonJacket = wizard.getAvailableCommand(DressWizard.JACKET_COMM);
		boolean result = jacketOnHot.valid(wizard, Temperature.COLD, putonJacket);
		Assert.assertTrue ( result);
	}
	
	@Test
	public void shouldHaveDoneJacketWearOnHot() {
		Rule jackonRule = new JacketOnRule();
		boolean result = jackonRule.valid(wizard, Temperature.HOT, null);
		Assert.assertTrue ( result);
	}
	
	@Test
	public void shouldHaveNotDonJackWearOnCold() {
		Rule jackonRule = new JacketOnRule();
		boolean result = jackonRule.valid(wizard, Temperature.COLD, null);
		Assert.assertFalse ( result);
	}
	
	@Test
	public void shouldHaveDonJackWearOnCold() {
		Rule jackonRule = new JacketOnRule();
		wizard.addWorkDone(new CommandEvent(DressWizard.JACKET_COMM, "jacket"));
		boolean result = jackonRule.valid(wizard, Temperature.COLD, null);
		Assert.assertTrue ( result);
	}
	
	
	@Test
	public void shouldOnlyDoJackOnOnce() {
		Rule onlyOne = new OnlyOneClothingRule();
		
		boolean result = onlyOne.valid(wizard, Temperature.HOT, wizard.getAvailableCommand(DressWizard.JACKET_COMM));
		Assert.assertTrue ( result);
	}
	
	@Test
	public void shouldNotDoJackTwice() {
		Rule onlyOne = new OnlyOneClothingRule();
		wizard.addWorkDone(new CommandEvent(DressWizard.JACKET_COMM, "jacket"));
		boolean result = onlyOne.valid(wizard, Temperature.HOT, wizard.getAvailableCommand(DressWizard.JACKET_COMM));
		Assert.assertFalse ( result);
	}
	
	@Test
	public void shouldHaveDoneHeadWear() {
		Rule headwearRule = new HeadWearRule();
		wizard.addWorkDone(new CommandEvent(DressWizard.HEAD_WEAR_COMM, "hat"));
		
		boolean result = headwearRule.valid(wizard, Temperature.HOT, null);
		Assert.assertTrue ( result);
	}
	
	@Test
	public void shouldHaveNotDoneHeadWear() {
		Rule headwearRule = new HeadWearRule();
		
		boolean result = headwearRule.valid(wizard, Temperature.HOT, null);
		Assert.assertFalse ( result);
	}
	
	@Test
	public void shouldOnlyDoHeadWearOnce() {
		Rule onlyOne = new OnlyOneClothingRule();
		
		boolean result = onlyOne.valid(wizard, Temperature.HOT, wizard.getAvailableCommand(DressWizard.HEAD_WEAR_COMM));
		Assert.assertTrue ( result);
	}
	
	@Test
	public void shouldNotDoHeadWearTwice() {
		Rule onlyOne = new OnlyOneClothingRule();
		wizard.addWorkDone( new CommandEvent(DressWizard.HEAD_WEAR_COMM, "hat"));
		boolean result = onlyOne.valid(wizard, Temperature.HOT, wizard.getAvailableCommand(DressWizard.HEAD_WEAR_COMM));
		Assert.assertFalse ( result);
	}
	
	@Test
	public void shouldBeInvalidIfSockOnHot() {
		Rule sockOnHot = new SocksHotRule();
		Command putonSock = wizard.getAvailableCommand(DressWizard.SOCK_COMM);
		boolean result = sockOnHot.valid(wizard, Temperature.HOT, putonSock);
		Assert.assertFalse ( result);
	}
	
	@Test
	public void shouldBeValidIfSockOnCold() {
		Rule sockOnHot = new SocksHotRule();
		Command putonSock = wizard.getAvailableCommand(DressWizard.SOCK_COMM);
		boolean result = sockOnHot.valid(wizard, Temperature.COLD, putonSock);
		Assert.assertTrue ( result);
	}
	
	@Test
	public void shouldBeValidToLeaveHouseOnCold() {
		Rule leaveHouseRule = new LeaveHouseRule();
		wizard.addWorkDone(new CommandEvent(DressWizard.FOORT_WEAR_COMM, "boots"));
		wizard.addWorkDone(new CommandEvent(DressWizard.HEAD_WEAR_COMM, "hat"));
		wizard.addWorkDone(new CommandEvent(DressWizard.SOCK_COMM, "sock"));
		wizard.addWorkDone(new CommandEvent(DressWizard.SHIRT_COMM, "shirt"));
		wizard.addWorkDone(new CommandEvent(DressWizard.JACKET_COMM, "jacket"));
		wizard.addWorkDone(new CommandEvent(DressWizard.PANTS_ON_COMM, "pants"));
		wizard.addWorkDone(new CommandEvent(DressWizard.PAJAMA_OFF_COMM, "pajamas"));
		boolean result = leaveHouseRule.valid(wizard, Temperature.COLD, null);
		Assert.assertTrue(result);
	}
	
	@Test
	public void shouldBeValidToLeaveHouseOnHot() {
		Rule leaveHouseRule = new LeaveHouseRule();
		wizard.addWorkDone(new CommandEvent(DressWizard.FOORT_WEAR_COMM, "sandals"));
		wizard.addWorkDone(new CommandEvent(DressWizard.HEAD_WEAR_COMM, "sunglass"));
		wizard.addWorkDone(new CommandEvent(DressWizard.SHIRT_COMM, "shirt"));
		wizard.addWorkDone(new CommandEvent(DressWizard.PANTS_ON_COMM, "shorts"));
		wizard.addWorkDone(new CommandEvent(DressWizard.PAJAMA_OFF_COMM, "pajamas"));
		
		boolean result = leaveHouseRule.valid(wizard, Temperature.HOT, null);
		Assert.assertTrue(result);
	}
	
	@Test
	public void shouldValidSockDoneOnHot() {
		Rule sockonRule = new SockOnRule();
		boolean result = sockonRule.valid(wizard, Temperature.HOT, null);
		Assert.assertTrue(result);
	}
	
	@Test
	public void shouldBeValidSockDoneOnCold() {
		Rule sockonRule = new SockOnRule();
		wizard.addWorkDone(new CommandEvent(DressWizard.SOCK_COMM, "socks"));
		boolean result = sockonRule.valid(wizard, Temperature.COLD, null);
		Assert.assertTrue(result);
	}
	
	@Test
	public void shouldBeInValidSockDoneOnCold() {
		Rule sockonRule = new SockOnRule();
		boolean result = sockonRule.valid(wizard, Temperature.COLD, null);
		Assert.assertFalse(result);
	}

}
