package org.confetti.rcp.wizards;

import org.confetti.rcp.wizards.models.NewTimetableModel;
import org.confetti.rcp.wizards.pages.EditNameAndCommentPage;
import org.confetti.rcp.wizards.pages.InsertEntriesWizardPage;
import org.confetti.rcp.wizards.pages.VerifyEntriesWizardPage;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Bubla Gabor
 */
public class NewTimetableWizard extends Wizard {

	private NewTimetableModel model;
	
	public NewTimetableWizard(NewTimetableModel model) {
		this.model = model;
		setWindowTitle("New timetable");
	}

	@Override
	public void addPages() {
		addPage(new EditNameAndCommentPage(model));
		addPage(new InsertEntriesWizardPage(model.getDaysModel()));
		addPage(new VerifyEntriesWizardPage(model.getDaysModel()));
		addPage(new InsertEntriesWizardPage(model.getHoursModel()));
		addPage(new VerifyEntriesWizardPage(model.getHoursModel()));
	}

	@Override
	public boolean performFinish() {
		return true;
	}


}
