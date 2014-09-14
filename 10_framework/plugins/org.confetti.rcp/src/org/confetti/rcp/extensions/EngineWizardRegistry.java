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
public enum EngineWizardRegistry {

	INSTANCE;
	
	/** The name of extension-point which is worked up. */
	private static final String GENERATEWIZARD_EXTENSION_POINT = "org.confetti.rcp.engineWizard"; //$NON-NLS-1$
	private List<EngineWizardDescr> descriptors = null;
	
	public List<EngineWizardDescr> getExtensions() {
		if (descriptors == null) {
			descriptors = loadDataProviders();
		}
		return descriptors;
	}
	
	private List<EngineWizardDescr> loadDataProviders() {
		List<EngineWizardDescr> res = new LinkedList<>();
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (registry == null) {
			return res;
		}
		IExtensionPoint point = registry.getExtensionPoint(GENERATEWIZARD_EXTENSION_POINT);
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

	private EngineWizardDescr createDescr(final IConfigurationElement element) throws CoreException {
		String name = element.getAttribute("name");
		EngineWizardFactory factory = (EngineWizardFactory) element.createExecutableExtension("factory");
		String author = element.getAttribute("author");

		return new EngineWizardDescr(name, factory, author);
	}
	
}
