package org.confetti.rcp.extensions;

/**
 * @author Gabor Bubla
 */
public class EngineWizardDescr {

	private final String name;
	private final EngineWizardFactory wizardFactory;
	private final String author;

	public EngineWizardDescr(String name, EngineWizardFactory wizardFactory, String author) {
		this.name = name;
		this.wizardFactory = wizardFactory;
		this.author = author;
	}

	public EngineWizardFactory getWizardFactory() {
		return wizardFactory;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public String getAuthor() {
		return author;
	}
	
}
