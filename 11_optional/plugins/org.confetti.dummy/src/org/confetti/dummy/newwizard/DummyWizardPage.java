package org.confetti.dummy.newwizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Bubla Gabor
 */
public class DummyWizardPage extends WizardPage {

	protected DummyWizardPage() {
		super("DummyPage", "DummyPage", null);
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		setControl(parent);
	}

}
