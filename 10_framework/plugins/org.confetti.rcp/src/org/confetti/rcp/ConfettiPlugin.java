package org.confetti.rcp;

import org.confetti.core.DataProvider;
import org.confetti.observable.ObservableValue;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class ConfettiPlugin extends AbstractUIPlugin {

	//The shared instance.
	private static ConfettiPlugin plugin;
	private ObservableValue<DataProvider> dp = new ObservableValue<>();
	
	/**
	 * The constructor.
	 */
	public ConfettiPlugin() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static ConfettiPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path.
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin("org.confetti.rcp", path);
	}
	
	public ObservableValue<DataProvider> getDataProvider() {
		return dp;
	}

	public void setDp(ObservableValue<DataProvider> dp) {
		this.dp = dp;
	}
	
}
