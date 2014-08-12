package org.confetti.rcp.wizards.models;

import java.util.List;

/**
 * @author Bubla Gabor
 */
public interface InsertEntriesModel {

	String getInsertEntriesPageTitle();
	String getInsertEntriesPageDescription();
	void addEntries(List<String> extractNames);

}
