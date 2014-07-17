package org.confetti.dummy.openwizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ChooseFileWizardPage extends WizardPage {

	private String text;

	protected ChooseFileWizardPage(String text) {
		super("chooseFile");
		this.text = text;
	}

	@Override
	public void createControl(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(text);
		setControl(label);
	}

}
