package org.confetti.dataprovider.db;

import org.confetti.rcp.extensions.ConnectionFactory;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @author Gabor Bubla
 */
public interface DbConnectionFactory extends ConnectionFactory {
    
    DbConnectionDescriptor createConnectionDescriptor(String connName, IPreferenceStore preferenceStore);
    
}
