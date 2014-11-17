package org.confetti.dataprovider.db.wizards;

import org.confetti.core.DataProvider;
import org.confetti.util.Tuple;

/**
 * @author Gabor Bubla
 */
public class CreateDatabaseWizardModel implements ChooseConnectionModel {

	private final DataProvider dp;
    private Tuple<String, String> connectionName;

    
	public CreateDatabaseWizardModel(DataProvider dp) {
		this.dp = dp;
	}

	@Override public void setConnectionName(Tuple<String, String> conn) { this.connectionName = conn; }
	@Override public Tuple<String, String> getSelectedConnection() { return connectionName; }

    public DataProvider getDataProvider() { return dp; }


}
