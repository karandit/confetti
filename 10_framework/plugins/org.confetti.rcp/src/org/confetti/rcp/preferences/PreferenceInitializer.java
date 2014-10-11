package org.confetti.rcp.preferences;

import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class PreferenceInitializer extends AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		IPreferenceStore store = ConfettiPlugin.getDefault().getPreferenceStore();
		
		store.setDefault(PreferenceConstants.DB_PLATFORM, "MySQL");
		store.setDefault(PreferenceConstants.DB_HOST, "localhost");
		store.setDefault(PreferenceConstants.DB_PORT, "3306");
		store.setDefault(PreferenceConstants.DB_DATABASE, "confetti");
		store.setDefault(PreferenceConstants.DB_USERNAME, "root");
		store.setDefault(PreferenceConstants.DB_PASSWORD, "root");
	}

}
