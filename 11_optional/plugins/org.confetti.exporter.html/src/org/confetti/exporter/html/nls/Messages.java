package org.confetti.exporter.html.nls;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.confetti.exporter.html.nls.messages"; //$NON-NLS-1$
	public static String ExportTimetableWizard_CouldNotCreateFiles;
	public static String ExportTimetableWizard_Error;
	public static String ExportTimetableWizard_Title;
	public static String FolderChooseWizardPage_Description;
	public static String FolderChooseWizardPage_Folder;
	public static String FolderChooseWizardPage_Name;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
