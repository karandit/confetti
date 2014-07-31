package org.confetti.rcp.wizards;

import org.eclipse.jface.wizard.Wizard;

/**
 * @author Bubla Gabor
 */
public class NewWizard extends Wizard {

	private NewWizardModel model;
	
	public NewWizard(NewWizardModel model) {
		this.model = model;
	}

	@Override
	public void addPages() {
		addPage(new NewWizardNamePage(model));
		addPage(new NewWizardDaysInputPage(model));
//		addPage(new NewWizardDaysVerifyPage(model));
//		addPage(new NewWizardHoursInputPage(model));
//		addPage(new NewWizardHoursVerifyPage(model));
	}

	@Override
	public boolean performFinish() {
		return true;
	}


}
