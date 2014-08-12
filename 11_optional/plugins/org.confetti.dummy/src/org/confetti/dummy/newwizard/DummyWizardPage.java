package org.confetti.dummy.newwizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Bubla Gabor
 */
public class DummyWizardPage extends WizardPage {

	DummyNewWizardModel model;
	
	protected DummyWizardPage(DummyNewWizardModel model) {
		super("DummyPage", "DummyPage", null);
		this.model = model;
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		Label institute = new Label(parent, SWT.NONE);
		institute.setText(model.getInstituteName());
		
		setControl(parent);
	}

}
