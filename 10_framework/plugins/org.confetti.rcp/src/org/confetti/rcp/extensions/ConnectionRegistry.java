package org.confetti.rcp.extensions;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * @author Gabor Bubla
 */
public enum ConnectionRegistry {

    INSTANCE;

    /** The name of extension-point which is worked up. */
    private static final String CONNECTION_EXTENSION_POINT = "org.confetti.rcp.connection"; //$NON-NLS-1$
    private List<ConnectionDescr> descriptors = null;
    
    public List<ConnectionDescr> getExtensions() {
        if (descriptors == null) {
            descriptors = loadDataProviders();
        }
        return descriptors;
    }

    public ConnectionDescr getConnectionByType(String typeName) {
        for (ConnectionDescr conn : getExtensions()) {
            if (conn.getDbType().equals(typeName)) {
                return conn;
            }
        }
        return null;
    }


    //------------------------- helpers --------------------------------------------------------------------------------
    private List<ConnectionDescr> loadDataProviders() {
        List<ConnectionDescr> res = new LinkedList<>();
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry == null) {
            return res;
        }
        IExtensionPoint point = registry.getExtensionPoint(CONNECTION_EXTENSION_POINT);
        if (point == null) {
            return res;
        }
        for (IExtension extension : point.getExtensions()) {
            if (null != extension) {
                for (IConfigurationElement element : extension.getConfigurationElements()) {
                    if (null != element) {
                        try {
                            res.add(createDescr(element));
                        } catch (CoreException e) {
                            //just ignore
                        }
                    }
                }
            }
        }
        return res;
    }

    private ConnectionDescr createDescr(final IConfigurationElement element) throws CoreException {
        String dbType = element.getAttribute("dbType");
        ConnectionFactory factory = (ConnectionFactory) element.createExecutableExtension("factory");

        return new ConnectionDescr(dbType, factory);
    }

    
}
