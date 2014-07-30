package org.confetti.dummy.openwizard;

import org.confetti.rcp.extensions.OpenWizardFactory;
import org.eclipse.jface.wizard.IWizard;

public class DummyOpenWizardFactory implements OpenWizardFactory {

	public DummyOpenWizardFactory() {
	}

	@Override
	public IWizard createWizard() {
		return new DummyOpenWizard();
	}

}
