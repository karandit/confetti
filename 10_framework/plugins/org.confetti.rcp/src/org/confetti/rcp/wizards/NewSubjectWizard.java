package org.confetti.rcp.wizards;

import org.eclipse.jface.wizard.Wizard;

public class NewSubjectWizard extends Wizard {

	private NewSubjectInputWizardPage subjectInputPage;
	private NewSubjectVerifyWizardPage subjectVerifyPage;
	
	private final NewSubjectInputWizardModel mModel = new NewSubjectInputWizardModel();
	
	public NewSubjectWizard() {
		setWindowTitle("New Subject");
		subjectInputPage = new NewSubjectInputWizardPage(mModel);
		subjectVerifyPage = new NewSubjectVerifyWizardPage(mModel);
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

//	@Override
//	public IWizardPage getNextPage(IWizardPage page) {
//		if (page.equals(subjectInputPage)) {
//			lines = subjectInputPage.getLines();
//		} else {
//			subjectVerifyPage = new NewSubjectVerifyWizardPage(lines);
//		}
//		return super.getNextPage(page);
//	}

}
