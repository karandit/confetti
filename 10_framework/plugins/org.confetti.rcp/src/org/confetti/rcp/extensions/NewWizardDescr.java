package org.confetti.rcp.extensions;

/**
 * @author Bubla Gabor
 */
public class NewWizardDescr {

	private final String name;
	private final NewWizardFactory wizardFactory;

	public NewWizardDescr(String name, NewWizardFactory wizardFactory) {
		this.name = name;
		this.wizardFactory = wizardFactory;
	}

	public NewWizardFactory getWizardFactory() {
		return wizardFactory;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
