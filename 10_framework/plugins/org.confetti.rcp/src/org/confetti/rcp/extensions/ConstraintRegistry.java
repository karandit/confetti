package org.confetti.rcp.extensions;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * @author Gabor Bubla
 */
public enum ConstraintRegistry {

    INSTANCE;
    
    /** The name of extension-point which is worked up. */
    private static final String CONSTRAINT_EXTENSION_POINT = "org.confetti.rcp.constraint"; //$NON-NLS-1$
    private List<ConstraintDescr> descriptors = null;
    
    public List<ConstraintDescr> getExtensions() {
        if (descriptors == null) {
            descriptors = loadDescriptors();
        }
        return descriptors;
    }

    //------------------------- helpers --------------------------------------------------------------------------------
    private List<ConstraintDescr> loadDescriptors() {
        List<ConstraintDescr> res = new LinkedList<>();
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry == null) {
            return res;
        }
        IExtensionPoint point = registry.getExtensionPoint(CONSTRAINT_EXTENSION_POINT);
        if (point == null) {
            return res;
        }
        for (IExtension extension : point.getExtensions()) {
            if (null != extension) {
                for (IConfigurationElement element : extension.getConfigurationElements()) {
                    if (null != element) {
                        res.add(new ConstraintDescr(element));
                    }
                }
            }
        }
        return res;
    }
    
}
