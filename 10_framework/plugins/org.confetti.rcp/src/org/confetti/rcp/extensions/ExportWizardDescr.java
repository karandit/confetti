package org.confetti.rcp.extensions;

public class ExportWizardDescr {
	
	private final String name;
	private final ExportWizardFactory wizardFactory;

	public ExportWizardDescr(String name, ExportWizardFactory wizardFactory) {
		this.name = name;
		this.wizardFactory = wizardFactory;
	}

	public ExportWizardFactory getWizardFactory() {
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
