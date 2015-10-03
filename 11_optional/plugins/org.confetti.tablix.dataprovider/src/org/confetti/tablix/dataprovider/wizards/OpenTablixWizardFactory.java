package org.confetti.tablix.dataprovider.wizards;

import org.confetti.rcp.extensions.OpenWizardFactory;
import org.eclipse.jface.wizard.IWizard;

public class OpenTablixWizardFactory implements OpenWizardFactory {

	@Override
	public IWizard createWizard() {
		return new OpenTablixWizard();
	}

}
