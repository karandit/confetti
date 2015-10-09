package org.confetti.rcp.wizards;

import org.confetti.core.DataProvider;
import org.confetti.rcp.extensions.ExportWizardFactory;
import org.eclipse.jface.wizard.IWizard;

public class HtmlExportWizardFactory implements ExportWizardFactory {

	@Override public IWizard createWizard(DataProvider dp) { return new ExportTimetableWizard(dp); }

}
