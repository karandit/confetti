package org.confetti.rcp.wizards;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

public class NewSubjectWizard extends Wizard {

	private NewSubjectInputWizardPage subjectInputPage;
	private NewSubjectVerifyWizardPage subjectVerifyPage;
	
	public NewSubjectWizard() {
		setWindowTitle("New Subject");
		subjectInputPage = new NewSubjectInputWizardPage();
		subjectVerifyPage = new NewSubjectVerifyWizardPage();
	}

	@Override
	public boolean performFinish() {
		return false;
	}

	@Override
	public void addPages() {
		addPage(subjectInputPage);
		addPage(subjectVerifyPage);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page.equals(subjectInputPage)) {
			System.out.println(subjectInputPage.getText());
		}
		return super.getNextPage(page);
	}

}
