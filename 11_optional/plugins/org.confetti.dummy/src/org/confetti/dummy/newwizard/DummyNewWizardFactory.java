package org.confetti.dummy.newwizard;

import java.util.List;

import org.confetti.rcp.extensions.NewWizardFactory;
import org.eclipse.jface.wizard.IWizard;

public class DummyNewWizardFactory implements NewWizardFactory {

	public DummyNewWizardFactory() {
	}

	@Override
	public IWizard createWizard(String instituteName, String comment, List<String> days, List<String> hours) {
		return new DummyNewWizard(instituteName, comment, days, hours);
	}

}
