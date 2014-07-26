package org.confetti.dummy.openwizard;

import org.eclipse.jface.wizard.Wizard;

public class DummyOpenWizard extends Wizard {

	@Override
	public void addPages() {
		addPage(new ChooseFileWizardPage("dummypage"));
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}

}
