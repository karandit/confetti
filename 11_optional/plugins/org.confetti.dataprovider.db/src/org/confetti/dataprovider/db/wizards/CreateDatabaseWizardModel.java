package org.confetti.dataprovider.db.wizards;

import org.confetti.core.DataProvider;
import org.confetti.dataprovider.db.ConnectionDescriptor;

/**
 * @author Gabor Bubla
 */
public class CreateDatabaseWizardModel implements ChooseConnectionModel {

	private final DataProvider dp;
    private ConnectionDescriptor cDesc;

	public CreateDatabaseWizardModel(DataProvider dp) {
		this.dp = dp;
	}

	public void setConnection(ConnectionDescriptor cDesc) { this.cDesc = cDesc; }
	public ConnectionDescriptor getConnection() { return cDesc; }

    public DataProvider getDataProvider() { return dp; }

}
