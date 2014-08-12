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
 * @author Bubla Gabor
 */
public enum NewWizardRegistry {

	INSTANCE;

	/** The name of extension-point which is worked up. */
	private static final String NEWWIZARD_EXTENSION_POINT = "org.confetti.rcp.newWizard"; //$NON-NLS-1$
	private List<NewWizardDescr> descriptors = null;
	
	public List<NewWizardDescr> getExtensions() {
		if (descriptors == null) {
			descriptors = loadDataProviders();
		}
		return descriptors;
	}
	
	private List<NewWizardDescr> loadDataProviders() {
		List<NewWizardDescr> res = new LinkedList<>();
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (registry == null) {
			return res;
		}
		IExtensionPoint point = registry.getExtensionPoint(NEWWIZARD_EXTENSION_POINT);
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

	private NewWizardDescr createDescr(final IConfigurationElement element) throws CoreException {
		String name = element.getAttribute("name");
		NewWizardFactory factory = (NewWizardFactory) element.createExecutableExtension("factory");

		return new NewWizardDescr(name, factory);
	}
	
}
