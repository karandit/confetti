package org.confetti.rcp.extensions;

/**
 * @author Bubla Gabor
 */
public class OpenWizardDescr {

	private final String name;
	private final OpenWizardFactory wizardFactory;

	public OpenWizardDescr(String name, OpenWizardFactory wizardFactory) {
		this.name = name;
		this.wizardFactory = wizardFactory;
	}

	public OpenWizardFactory getWizardFactory() {
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
