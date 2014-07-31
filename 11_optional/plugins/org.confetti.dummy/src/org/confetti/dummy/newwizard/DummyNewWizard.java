package org.confetti.dummy.newwizard;

import java.util.List;

import org.eclipse.jface.wizard.Wizard;

public class DummyNewWizard extends Wizard {

	DummyNewWizardModel model = new DummyNewWizardModel();
	
	public DummyNewWizard(String instituteName, String comment, List<String> days, List<String> hours) {
		super();
		model.setInstituteName(instituteName);
		model.setComment(comment);
		model.setDays(days);
		model.setHours(hours);
	}

	@Override
	public void addPages() {
		addPage(new DummyWizardPage(model));
	}
	
	@Override
	public boolean performFinish() {
		return false;
	}

}
