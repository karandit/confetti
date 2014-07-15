package org.confetti.rcp.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

public class NewSubjectVerifyWizardPage extends WizardPage {

	protected NewSubjectVerifyWizardPage() {
		super("Verify", "The following subjects will be added!", null);
	}

	@Override
	public void createControl(Composite parent) {
		setControl(parent);
		setPageComplete(true);
	}

}
