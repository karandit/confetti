package org.confetti.dataprovider.wizards;

import org.confetti.core.DataProvider;
import org.confetti.rcp.extensions.NewWizardFactory;
import org.eclipse.jface.wizard.IWizard;

/**
 * @author Gabor Bubla
 */
public class NewXmlWizardFactory implements NewWizardFactory {

    @Override
    public IWizard createWizard(DataProvider dp) {
        NewXmlWizardModel model = new NewXmlWizardModel(dp);
        return new NewXmlWizard(model);
    }

}
