package org.confetti.dummy.generateWizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Gabor Bubla
 */
public class DummyEngineWizardPage extends WizardPage {

	protected DummyEngineWizardPage(String pageName) {
		super(pageName);
	}

	@Override
	public void createControl(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("Generating blabla");
		setControl(label);
	}

}
