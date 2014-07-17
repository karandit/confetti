package org.confetti.rcp.extensions;

import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;

public interface OpenWizardFactory {

	List<IWizardPage> getPages();
	
}
