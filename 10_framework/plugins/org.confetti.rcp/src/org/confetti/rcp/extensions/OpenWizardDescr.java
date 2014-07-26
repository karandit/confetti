package org.confetti.rcp.extensions;

import org.eclipse.jface.wizard.IWizard;

public class OpenWizardDescr {

	private final String name;
	private final IWizard wizard;

	public OpenWizardDescr(String name, IWizard wizard) {
		this.name = name;
		this.wizard = wizard;
	}

	public IWizard getWizard() {
		return wizard;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
