package org.confetti.dummy.generateWizard;

import org.confetti.core.DataProvider;
import org.confetti.rcp.extensions.EngineWizardFactory;
import org.eclipse.jface.wizard.IWizard;

/**
 * @author Gabor Bubla
 */
public class DummyEngineWizardFactory implements EngineWizardFactory {

	public DummyEngineWizardFactory() {
	}

	@Override
	public IWizard createWizard(DataProvider dp) {
		return new DummyEngineWizard(dp);
	}

}
