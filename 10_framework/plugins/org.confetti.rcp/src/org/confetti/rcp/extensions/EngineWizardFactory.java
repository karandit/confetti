package org.confetti.rcp.extensions;

import org.confetti.core.DataProvider;
import org.eclipse.jface.wizard.IWizard;

/**
 * @author Gabor Bubla
 */
public interface EngineWizardFactory {

	IWizard createWizard(DataProvider dp);
	
}
