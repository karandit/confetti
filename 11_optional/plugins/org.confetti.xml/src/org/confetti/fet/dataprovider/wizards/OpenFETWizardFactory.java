package org.confetti.fet.dataprovider.wizards;

import org.confetti.rcp.extensions.OpenWizardFactory;
import org.eclipse.jface.wizard.IWizard;

public class OpenFETWizardFactory implements OpenWizardFactory {

	@Override
	public IWizard createWizard() {
		return new OpenFETWizard();
	}


}
