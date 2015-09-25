package org.confetti.rcp.wizards;

import org.confetti.rcp.nls.Messages;
import org.confetti.rcp.wizards.models.EditInstituteModel;
import org.confetti.rcp.wizards.pages.EditNameAndCommentPage;
import org.confetti.rcp.wizards.pages.IWizardPageNavigatable;
import org.eclipse.jface.wizard.Wizard;

public class EditInstituteWizard extends Wizard {

	private EditInstituteModel model;
	
	public EditInstituteWizard(EditInstituteModel model) {
		this.model = model;
		setWindowTitle(Messages.EditInstituteWizard_Title);
	}

	@Override
	public void addPages() {
		addPage(new EditNameAndCommentPage(model));
	}

	@Override
	public boolean performFinish() {
		((IWizardPageNavigatable) getPages()[0]).pageHid(); 
		return true;
	}
}
