package org.confetti.dataprovider.db.wizards;

import org.confetti.core.DataProvider;
import org.confetti.rcp.extensions.NewWizardFactory;
import org.eclipse.jface.wizard.IWizard;

/**
 * @author Gabor Bubla
 */
public class CreateDatabaseWizardFactory implements NewWizardFactory {

    @Override
    public IWizard createWizard(DataProvider dp) {
        CreateDatabaseWizardModel model = new CreateDatabaseWizardModel(dp);
        return new CreateDatabaseWizard(model);
    }

}
