package org.confetti.rcp.wizards;

import org.eclipse.jface.wizard.Wizard;

public class OpenWizard extends Wizard {

	public OpenWizard() {
		setWindowTitle("Open");
		setForcePreviousAndNextButtons(true);
	}
	
	@Override
	public boolean performFinish() {
		return true;
	}

	@Override
	public void addPages() {
		addPage(new ChooseDataProviderWizardPage());
	}

	
}
