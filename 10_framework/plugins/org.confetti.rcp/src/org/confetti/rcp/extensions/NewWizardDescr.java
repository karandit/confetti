package org.confetti.rcp.extensions;

import org.eclipse.jface.wizard.IWizard;

/**
 * @author Bubla Gabor
 */
public class NewWizardDescr {

	private final String name;
	private final IWizard wizard;

	public NewWizardDescr(String name, IWizard wizard) {
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
