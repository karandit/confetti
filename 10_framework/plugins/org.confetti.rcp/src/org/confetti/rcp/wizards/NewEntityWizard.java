package org.confetti.rcp.wizards;

import org.eclipse.jface.wizard.Wizard;

public class NewEntityWizard extends Wizard {

	private final NewEntityWizardModel<?> mModel;
	
	public NewEntityWizard(final NewEntityWizardModel<?> model) {
		this.mModel = model;
		setWindowTitle(model.getWizardTitle());
	}

	@Override
	public boolean performFinish() {
		mModel.createEntities();
		return true;
	}

	@Override
	public void addPages() {
		addPage(new InsertEntityNamesWizardPage(mModel));
		addPage(new VerifyEntityNamesWizardPage(mModel));
	}

}
