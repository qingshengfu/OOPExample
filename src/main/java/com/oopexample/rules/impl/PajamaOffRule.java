package com.oopexample.rules.impl;

import com.oopexample.DressWizard;
import com.oopexample.Temperature;
import com.oopexample.command.impl.Command;
import com.oopexample.rules.Rule;

public class PajamaOffRule implements Rule {

	

	@Override
	public boolean valid(DressWizard context, Command command) {
		return context.isTaskDone(DressWizard.PAJAMA_OFF_COMM);
	}

}
