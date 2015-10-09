package org.confetti.rcp.extensions;

import org.confetti.core.DataProvider;
import org.eclipse.jface.wizard.IWizard;

public interface ExportWizardFactory {
	
	IWizard createWizard(DataProvider dp);

}
