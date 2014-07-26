package org.confetti.rcp.extensions;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.wizard.IWizard;

public enum OpenWizardRegistry {

	INSTANCE;

	/** The name of extension-point which is worked up. */
	private static final String OPENWIZARD_EXTENSION_POINT = "org.confetti.rcp.openWizard"; //$NON-NLS-1$
	private List<OpenWizardDescr> descriptors = null;
	
	public List<OpenWizardDescr> getExtensions() {
		if (descriptors == null) {
			descriptors = loadDataProviders();
		}
		return descriptors;
	}
	
	private List<OpenWizardDescr> loadDataProviders() {
		List<OpenWizardDescr> res = new LinkedList<>();
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (registry == null) {
			return res;
		}
		IExtensionPoint point = registry.getExtensionPoint(OPENWIZARD_EXTENSION_POINT);
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

	private OpenWizardDescr createDescr(final IConfigurationElement element) throws CoreException {
		String name = element.getAttribute("name");
		IWizard wizard = (IWizard) element.createExecutableExtension("wizard");

		return new OpenWizardDescr(name, wizard);
	}
	
}
