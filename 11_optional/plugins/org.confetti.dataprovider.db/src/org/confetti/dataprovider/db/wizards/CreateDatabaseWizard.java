package org.confetti.dataprovider.db.wizards;

import java.util.List;

import org.eclipse.jface.wizard.Wizard;

/**
 * @author Gabor Bubla
 */
public class CreateDatabaseWizard extends Wizard {

	private final CreateDatabaseWizardModel model;
	
	public CreateDatabaseWizard(String instituteName, String comment, List<String> days, List<String> hours) {
		model = new CreateDatabaseWizardModel(instituteName, comment, days, hours);
	}
	
	@Override
	public void addPages() {
		super.addPages();
		addPage(new ChooseConnectionWizardPage(model));
	}

	@Override
	public boolean performFinish() {
		return true;
	}
	
}
