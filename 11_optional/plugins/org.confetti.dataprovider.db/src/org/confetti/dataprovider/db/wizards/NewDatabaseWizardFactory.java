package org.confetti.dataprovider.db.wizards;

import org.confetti.core.DataProvider;
import org.confetti.rcp.extensions.NewWizardFactory;
import org.eclipse.jface.wizard.IWizard;

/**
 * @author Gabor Bubla
 */
public class NewDatabaseWizardFactory implements NewWizardFactory {

    @Override
    public IWizard createWizard(DataProvider dp) {
        NewDatabaseWizardModel model = new NewDatabaseWizardModel(dp);
        return new NewDatabaseWizard(model);
    }

}
