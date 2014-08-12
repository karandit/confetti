package org.confetti.dataprovider.wizards;

import org.confetti.rcp.extensions.OpenWizardFactory;
import org.eclipse.jface.wizard.IWizard;

public class OpenXmlWizardFactory implements OpenWizardFactory {

	@Override
	public IWizard createWizard() {
		return new OpenXmlWizard();
	}


}
