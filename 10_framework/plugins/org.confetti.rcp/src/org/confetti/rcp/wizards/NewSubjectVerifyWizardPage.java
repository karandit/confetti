package org.confetti.rcp.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class NewSubjectVerifyWizardPage extends WizardPage {

	private String[] lines;

	public NewSubjectVerifyWizardPage(String[] lines) {
		super("Verify", "The following subjects will be added!", null);
		this.lines = lines;
	}

	@Override
	public void createControl(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
//		label.setText(lines.toString());
		setControl(parent);
		setPageComplete(true);
	}

	@Override
	public void setPageComplete(boolean complete) {
		super.setPageComplete(complete);
	}

}
