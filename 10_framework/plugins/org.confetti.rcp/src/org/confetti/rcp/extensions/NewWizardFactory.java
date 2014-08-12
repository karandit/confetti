package org.confetti.rcp.extensions;

import java.util.List;

import org.eclipse.jface.wizard.IWizard;

public interface NewWizardFactory {
	
	IWizard createWizard(String instituteName, String comment, List<String> days, List<String> hours);

}
