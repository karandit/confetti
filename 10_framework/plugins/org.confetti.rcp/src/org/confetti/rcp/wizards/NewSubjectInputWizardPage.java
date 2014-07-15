package org.confetti.rcp.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;

public class NewSubjectInputWizardPage extends WizardPage {

	private StyledText text;

	protected NewSubjectInputWizardPage() {
		super("Input", "Every new line will be a new Subject!", null);
	}

	@Override
	public void createControl(Composite parent) {
		text = new StyledText(parent, SWT.NONE);
		text.setEnabled(true);
		setControl(parent);
	}

	@Override
	public boolean canFlipToNextPage() {
//		System.out.println(!text.getText().isEmpty());
		return true;
	}

	public StyledText getText() {
		return text;
	}
	
}
