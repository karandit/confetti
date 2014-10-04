package org.confetti.dataprovider.db.wizards;

import org.confetti.dataprovider.db.ConnectionDescriptor;

/**
 * @author Gabor Bubla
 */
public interface ChooseConnectionModel {

    void setConnection(ConnectionDescriptor cDesc);
    ConnectionDescriptor getConnection();
    
}
