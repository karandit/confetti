package org.confetti.dataprovider.db.wizards;

import java.util.List;

import org.confetti.rcp.extensions.NewWizardFactory;
import org.eclipse.jface.wizard.IWizard;

/**
 * @author Gabor Bubla
 */
public class CreateDatabaseWizardFactory implements NewWizardFactory {

	public CreateDatabaseWizardFactory() {
	}

	@Override
	public IWizard createWizard(String instituteName, String comment, List<String> days, List<String> hours) {
		return new CreateDatabaseWizard(instituteName, comment, days, hours);
	}

}
