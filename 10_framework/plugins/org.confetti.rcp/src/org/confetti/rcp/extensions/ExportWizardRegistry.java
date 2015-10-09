package org.confetti.rcp.extensions;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

public enum ExportWizardRegistry {

	INSTANCE;

	/** The name of extension-point which is worked up. */
	private static final String EXPORTWIZARD_EXTENSION_POINT = "org.confetti.rcp.exportWizard"; //$NON-NLS-1$
	private List<ExportWizardDescr> descriptors = null;
	
	public List<ExportWizardDescr> getExtensions() {
		if (descriptors == null) {
			descriptors = loadDataProviders();
		}
		return descriptors;
	}
	
	private List<ExportWizardDescr> loadDataProviders() {
		List<ExportWizardDescr> res = new LinkedList<>();
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (registry == null) {
			return res;
		}
		IExtensionPoint point = registry.getExtensionPoint(EXPORTWIZARD_EXTENSION_POINT);
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

	private ExportWizardDescr createDescr(final IConfigurationElement element) throws CoreException {
		String name = element.getAttribute("name"); //$NON-NLS-1$
		ExportWizardFactory factory = (ExportWizardFactory) element.createExecutableExtension("factory"); //$NON-NLS-1$

		return new ExportWizardDescr(name, factory);
	}
	
}
