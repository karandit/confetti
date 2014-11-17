package org.confetti.dataprovider.db.wizards;

import org.confetti.util.Tuple;

/**
 * @author Gabor Bubla
 */
public class OpenDatabaseWizardModel implements ChooseConnectionModel {

    private Tuple<String, String> connectionName;

    public OpenDatabaseWizardModel() {
    }

    @Override public Tuple<String, String> getSelectedConnection()      { return connectionName; }
    @Override public void setConnectionName(Tuple<String, String> conn) { this.connectionName = conn; }

}
