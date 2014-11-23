package org.confetti.dataprovider.db.wizards;

import org.confetti.util.Tuple;

/**
 * @author Gabor Bubla
 */
public interface ChooseConnectionModel {

    void setConnectionName(Tuple<String, String> connectionName);
    Tuple<String, String> getSelectedConnection();
    
}
