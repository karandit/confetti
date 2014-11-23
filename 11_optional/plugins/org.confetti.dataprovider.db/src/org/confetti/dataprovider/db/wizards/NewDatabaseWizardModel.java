package org.confetti.dataprovider.db.wizards;

import org.confetti.core.DataProvider;
import org.confetti.util.Tuple;

/**
 * @author Gabor Bubla
 */
public class NewDatabaseWizardModel implements ChooseConnectionModel {

	private final DataProvider dp;
    private Tuple<String, String> connectionName;
    
	public NewDatabaseWizardModel(DataProvider dp) {
		this.dp = dp;
	}

	@Override public Tuple<String, String> getSelectedConnection()      { return connectionName; }
	@Override public void setConnectionName(Tuple<String, String> conn) { this.connectionName = conn; }
    public DataProvider getDataProvider()                               { return dp; }

}
