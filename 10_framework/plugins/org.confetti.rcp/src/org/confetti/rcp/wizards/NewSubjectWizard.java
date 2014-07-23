package org.confetti.rcp.wizards;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

public class NewSubjectWizard extends Wizard {

	private String[] lines;
	
	private NewSubjectInputWizardPage subjectInputPage;
	private NewSubjectVerifyWizardPage subjectVerifyPage;
	
	public NewSubjectWizard() {
		setWindowTitle("New Subject");
		subjectInputPage = new NewSubjectInputWizardPage(lines);
		subjectVerifyPage = new NewSubjectVerifyWizardPage(lines);
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
//		if (page.equals(subjectInputPage)) {
//			lines = subjectInputPage.getLines();
//		} else {
//			subjectVerifyPage = new NewSubjectVerifyWizardPage(lines);
//		}
		return super.getNextPage(page);
	}

}
