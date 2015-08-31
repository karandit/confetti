package org.confetti.rcp;

import java.util.LinkedList;
import java.util.List;

import org.confetti.core.DataPersister;
import org.confetti.core.DataProvider;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;
import org.confetti.util.Tuple;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class ConfettiPlugin 
implements org.osgi.framework.BundleActivator
//extends AbstractUIPlugin 
{

    //--------------------------- constants ----------------------------------------------------------------------------
	public static final String IMG_SMALL_SUBJECT 		= "small_subject";
	public static final String IMG_SMALL_TEACHER 		= "small_teacher";
	public static final String IMG_SMALL_STUDENTGROUP 	= "small_studentgroup";
	public static final String IMG_SMALL_ROOM 			= "small_room";

	public static final String IMG_BIG_SUBJECT			= "big_subject";
	public static final String IMG_BIG_TEACHER			= "big_teacher";
	public static final String IMG_BIG_STUDENTGROUP		= "big_studentgroup";
	public static final String IMG_BIG_ROOM 			= "big_room";

	public static final String IMG_BIG_HOUR 			= "big_hour";
	public static final String IMG_BIG_DAY 				= "big_day";
	public static final String IMG_BIG_INSTITUTE		= "big_institute";
	public static final String IMG_BIG_TIMETABLE		= "big_timetable";
	
	public static final String IMG_BIG_CLOUD 			= "big_cloud";
	public static final String IMG_BIG_FILE				= "big_file";
	public static final String IMG_BIG_DB				= "big_db";
	public static final String IMG_BIG_ENGINE			= "big_engine";
	
	//TODO remove these sample icons
	public static final String IMG_SAMPLE				= "sample";
	public static final String IMG_SAMPLE2				= "sample2";
	public static final String IMG_SAMPLE3				= "sample3";

	
	//---preference related stuff ----------------------------
    public static final String KEY_CONNECTIONS = "CONNECTIONS";
    public static final String KEY_TYPE = "TYPE";

	
	//The shared instance.
	private static ConfettiPlugin plugin;
	private ValueMutator<DataProvider> dpMutator = new ValueMutator<>();
    private DataPersister dataPersister;
	
	/**
	 * The constructor.
	 */
	public ConfettiPlugin() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("ConfettiPlugin.start() *****************************************************");
		try {
//			super.start(context);
			
		} catch (Exception e) {
			System.out.println("ConfettiPlugin.start() EXCEPTION $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("ConfettiPlugin.stop() #############################################################");
//		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static ConfettiPlugin getDefault() {
		return plugin;
	}
	
//	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		//Library-Books-16.png
		//Teacher-02-16.png
		registerImage(reg, IMG_SMALL_SUBJECT, 		"subject-16.png"); //$NON-NLS-1$
		registerImage(reg, IMG_SMALL_TEACHER, 		"teacher-16.png"); //$NON-NLS-1$
		registerImage(reg, IMG_SMALL_STUDENTGROUP,	"studentgroup-16.png"); //$NON-NLS-1$
		registerImage(reg, IMG_SMALL_ROOM, 			"room-16.png"); //$NON-NLS-1$

		registerImage(reg, IMG_BIG_SUBJECT, 		"subject-48-2.png"); //$NON-NLS-1$
		registerImage(reg, IMG_BIG_TEACHER, 		"teacher-48.png"); //$NON-NLS-1$
		registerImage(reg, IMG_BIG_STUDENTGROUP,	"studentgroup-48.png"); //$NON-NLS-1$
		registerImage(reg, IMG_BIG_ROOM, 			"room-48.png"); //$NON-NLS-1$
		registerImage(reg, IMG_BIG_HOUR, 			"hour-48.png"); //$NON-NLS-1$
		registerImage(reg, IMG_BIG_DAY, 			"day-48.png"); //$NON-NLS-1$
		
		registerImage(reg, IMG_BIG_INSTITUTE, 		"university-48.png"); //$NON-NLS-1$
		registerImage(reg, IMG_BIG_TIMETABLE, 		"timetable-48.png"); //$NON-NLS-1$
		registerImage(reg, IMG_BIG_CLOUD, 			"cloud-48.png"); //$NON-NLS-1$
		registerImage(reg, IMG_BIG_FILE, 			"file-48.png"); //$NON-NLS-1$
		registerImage(reg, IMG_BIG_DB, 				"db-48.png"); //$NON-NLS-1$
		registerImage(reg, IMG_BIG_ENGINE, 			"engine-48.png"); //$NON-NLS-1$
		
		//TODO remove these sample icons
		registerImage(reg, IMG_SAMPLE, 				"sample.gif"); //$NON-NLS-1$
		registerImage(reg, IMG_SAMPLE2, 			"sample2.gif"); //$NON-NLS-1$
		registerImage(reg, IMG_SAMPLE3, 			"sample3.gif"); //$NON-NLS-1$
	}

	/**
	 * Loads and registers an image of this plug-in.
	 * @param registry the storage for the images.
	 * @param key the key used in the registry.
	 * @param fileName the path of the image, relative to the <code>icons/</code> directory of this plug-in.
	 */
	private void registerImage(final ImageRegistry registry, final String key, final String fileName) {
//		try {
//			final IPath path = new Path("icons/" + fileName); //$NON-NLS-1$
//			// URL url = find(path); // deprecated
//			final URL url = FileLocator.find(getBundle(), path, null);
//			if (url != null) {
//				final ImageDescriptor desc = ImageDescriptor.createFromURL(url);
//				registry.put(key, desc);
//			}
//		} catch (final Exception e) {
//			//ignore it
//		}
	}
	
	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path.
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String key) {
		return null; //getDefault().getImageRegistry().getDescriptor(key);
	}
	
	public ObservableValue<DataProvider> getDataProvider() {
		return dpMutator.getObservableValue();
	}

	public DataPersister getDataPersister() {
	    return dataPersister;
	}

	public void setDataProvider(DataProvider value, DataPersister dataPersister) {
		this.dataPersister = dataPersister;
        dpMutator.setValue(this, value);
        if (value != null) {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().setText("Confetti - " + value.getInformation());
        }
	}
	
    public List<Tuple<String, String>> getConnectionSettings() {
//	    IPreferenceStore preferenceStore = getPreferenceStore();
	    List<Tuple<String, String>> connNamesAndTypes = new LinkedList<>();
//        String connNamesCSV = preferenceStore.getString(KEY_CONNECTIONS);
//        String[] connNames = connNamesCSV.split(",");
//        for (String connName : connNames) {
//            if (!connName.isEmpty()) {
//                String connType = preferenceStore.getString(connName + "_" + KEY_TYPE);
//                connNamesAndTypes.add(new Tuple<>(connName, connType));
//            }
//        }
        return connNamesAndTypes;
	}
}
