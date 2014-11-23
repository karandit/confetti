package org.confetti.dataprovider.db.wizards;

import org.confetti.rcp.extensions.OpenWizardFactory;
import org.eclipse.jface.wizard.IWizard;

/**
 * @author Gabor Bubla
 */
public class OpenDatabaseWizardFactory implements OpenWizardFactory {

    @Override
    public IWizard createWizard() {
        return new OpenDatabaseWizard();
    }

}
