package org.confetti.rcp.extensions;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

public enum DataProviderRegistry {

	INSTANCE;

	/** The name of extension-point which is worked up. */
	private static final String DATAPROVIDER_EXTENSION_POINT = "org.confetti.rcp.dataProvider"; //$NON-NLS-1$
	private List<DataProviderDescr> descriptors = null;
	
	public List<DataProviderDescr> getDataProviders() {
		if (descriptors == null) {
			descriptors = loadDataProviders();
		}
		return descriptors;
	}
	
	private List<DataProviderDescr> loadDataProviders() {
		List<DataProviderDescr> res = new LinkedList<>();
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (registry == null) {
			return res;
		}
		IExtensionPoint point = registry.getExtensionPoint(DATAPROVIDER_EXTENSION_POINT);
		if (point == null) {
			return res;
		}
		for (IExtension extension : point.getExtensions()) {
			if (null != extension) {
				for (IConfigurationElement element : extension.getConfigurationElements()) {
					if (null != element) {
						res.add(createDataProviderDescr(element));
					}
				}
			}
		}
		return res;
	}

	private DataProviderDescr createDataProviderDescr(final IConfigurationElement element) {
		String name = element.getAttribute("name");
		return new DataProviderDescr(name);
	}
	
}
