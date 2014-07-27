package org.confetti.dummy.openwizard;

import org.eclipse.jface.wizard.Wizard;

public class DummyOpenWizard extends Wizard {

	ChooseFileWizardPage chooseFile;
	
	public DummyOpenWizard() {
		super();
		chooseFile = new ChooseFileWizardPage();
	}

	@Override
	public void addPages() {
		addPage(chooseFile);
	}

	@Override
	public boolean performFinish() {
		//TODO:Open the selected file with
		//chooseFile.getPath();
		return true;
	}

}
