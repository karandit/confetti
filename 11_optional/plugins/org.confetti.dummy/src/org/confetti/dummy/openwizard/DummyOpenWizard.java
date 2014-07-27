package org.confetti.dummy.openwizard;

import org.eclipse.jface.wizard.Wizard;

public class DummyOpenWizard extends Wizard {

	@Override
	public void addPages() {
		addPage(new ChooseFileWizardPage());
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
