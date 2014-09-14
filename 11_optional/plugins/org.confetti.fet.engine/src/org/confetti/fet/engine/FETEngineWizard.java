package org.confetti.fet.engine;

import org.eclipse.jface.wizard.Wizard;

/**
 * @author Gabor Bubla
 */
public class FETEngineWizard extends Wizard {

	@Override
	public void addPages() {
		setWindowTitle("Generate");
		addPage(new FETEngineWizardPage("FET Wizard Page"));
	}
	
	@Override
	public boolean performFinish() {
		return false;
	}
	
}
