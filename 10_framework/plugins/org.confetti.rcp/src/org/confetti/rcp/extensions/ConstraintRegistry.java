package org.confetti.rcp.extensions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.confetti.rcp.constraints.IConstraintRegistry;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * @author Gabor Bubla
 */
public enum ConstraintRegistry implements IConstraintRegistry {

    INSTANCE;
    
    /** The name of extension-point which is worked up. */
    private static final String CONSTRAINT_EXTENSION_POINT = "org.confetti.rcp.constraint"; //$NON-NLS-1$
    private List<IConstraintElement> descriptors = null;
    private Map<String, ConstraintDescr> constraintDescrsById = null;
    
    public List<IConstraintElement> getExtensions() {
        init();
        return descriptors;
    }
    
    @Override
	public ConstraintDescr getConstraintDescrById(String id) {
        init();
    	return constraintDescrsById.get(id);
    }

	//------------------------- helpers --------------------------------------------------------------------------------
	private void init() {
		if (descriptors == null) {
            descriptors = loadDescriptors();
            IConstraintElement[] descrArr = descriptors.toArray(new IConstraintElement[descriptors.size()]);
            constraintDescrsById = loadConstDescrsByType(descrArr);
        }
	}

	private static List<IConstraintElement> loadDescriptors() {
        List<IConstraintElement> res = new LinkedList<>();
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
                    	ConstraintElementParser parser = ConstraintElementParser.getByName(element.getName());
						res.add(parser.parse(element));
                    }
                }
            }
        }
        return res;
    }

    private static Map<String, ConstraintDescr> loadConstDescrsByType(IConstraintElement[] elements) {
    	Map<String, ConstraintDescr> res = new HashMap<>();
    	for (IConstraintElement element : elements) {
			if (element instanceof ConstraintDescr) {
				ConstraintDescr descr = (ConstraintDescr) element;
				res.put(descr.getId(), descr);
			}
    		if (element.hasChildren()) {
				res.putAll(loadConstDescrsByType(element.getChildren()));
			}
		}
    	return res;
	}
}
