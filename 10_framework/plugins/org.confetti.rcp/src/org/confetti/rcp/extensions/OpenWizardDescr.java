package org.confetti.rcp.extensions;

public class OpenWizardDescr {

	private final String name;
	private final OpenWizardFactory factory;

	public OpenWizardDescr(String name, OpenWizardFactory factory) {
		this.name = name;
		this.factory = factory;
	}

	public OpenWizardFactory getFactory() {
		return factory;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
