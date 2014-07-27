package org.confetti.dummy.newwizard;

import org.eclipse.jface.wizard.Wizard;

public class DummyNewWizard extends Wizard {

	public DummyNewWizard() {
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
