package org.confetti.rcp.wizards;

import org.confetti.rcp.wizards.models.NewEntityWizardModel;
import org.confetti.rcp.wizards.pages.InsertEntriesWizardPage;
import org.confetti.rcp.wizards.pages.VerifyEntriesWizardPage;
import org.eclipse.jface.wizard.Wizard;

public class NewEntityWizard extends Wizard {

	private final NewEntityWizardModel<?> mModel;
	
	public NewEntityWizard(final NewEntityWizardModel<?> model) {
		this.mModel = model;
		setWindowTitle(model.getWizardTitle());
	}

	@Override
	public void addPages() {
		addPage(new InsertEntriesWizardPage(mModel));
		addPage(new VerifyEntriesWizardPage(mModel));
	}

	@Override
	public boolean performFinish() {
		mModel.createEntities();
		return true;
	}


}
