package org.confetti.fet.dataprovider.wizards;

import org.confetti.core.DataProvider;
import org.confetti.rcp.extensions.NewWizardFactory;
import org.eclipse.jface.wizard.IWizard;

/**
 * @author Gabor Bubla
 */
public class NewFETWizardFactory implements NewWizardFactory {

    @Override
    public IWizard createWizard(DataProvider dp) {
        NewFETWizardModel model = new NewFETWizardModel(dp);
        return new NewFETWizard(model);
    }

}
