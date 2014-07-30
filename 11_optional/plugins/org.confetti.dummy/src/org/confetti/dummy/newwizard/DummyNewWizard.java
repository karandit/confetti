package org.confetti.dummy.newwizard;

import java.util.List;

import org.eclipse.jface.wizard.Wizard;

public class DummyNewWizard extends Wizard {

	public DummyNewWizard(String instituteName, String comment, List<String> days, List<String> hours) {
		//TODO: create a model and give it the parameters
		super();
	}

	@Override
	public void addPages() {
		addPage(new DummyWizardPage());
	}
	
	@Override
	public boolean performFinish() {
		return false;
	}

}
