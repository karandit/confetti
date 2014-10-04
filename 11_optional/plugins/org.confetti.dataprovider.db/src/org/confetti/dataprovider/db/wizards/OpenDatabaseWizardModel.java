package org.confetti.dataprovider.db.wizards;

import org.confetti.dataprovider.db.ConnectionDescriptor;

/**
 * @author Gabor Bubla
 */
public class OpenDatabaseWizardModel implements ChooseConnectionModel {

    private ConnectionDescriptor cDesc;

    public OpenDatabaseWizardModel() {
    }

    public void setConnection(ConnectionDescriptor cDesc) { this.cDesc = cDesc; }
    public ConnectionDescriptor getConnection() { return cDesc; }

}
