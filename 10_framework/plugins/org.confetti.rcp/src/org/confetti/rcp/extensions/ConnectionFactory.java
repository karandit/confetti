package org.confetti.rcp.extensions;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Gabor Bubla
 */
public interface ConnectionFactory {

    Composite createComposite(Composite parent);
    void save(String connName, IPreferenceStore preferenceStore);
    void load(String connName, IPreferenceStore preferenceStore);
    
}
