package org.confetti.dummy.generateWizard;

import org.confetti.core.DataProvider;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Gabor Bubla
 */
public class DummyEngineWizard extends Wizard {

	public DummyEngineWizard(DataProvider dp) {
		addPage(new DummyEngineWizardPage("GeneratePage"));
	}

	@Override
	public boolean performFinish() {
		return false;
	}

}
