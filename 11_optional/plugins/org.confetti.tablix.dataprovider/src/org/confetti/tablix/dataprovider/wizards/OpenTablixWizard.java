package org.confetti.tablix.dataprovider.wizards;

import org.confetti.rcp.ConfettiPlugin;
import org.confetti.tablix.dataprovider.TablixDataProvider;
import org.confetti.tablix.xml.TablixFAO;
import org.confetti.tablix.xml.TablixXml;
import org.confetti.xml.FAOException;
import org.eclipse.jface.wizard.Wizard;

public class  OpenTablixWizard extends Wizard {

	private final OpenTablixWizardModel model = new OpenTablixWizardModel();
	
	@Override
	public void addPages() {
		setWindowTitle("Open");
		addPage(new ChooseTablixFileWizardPage(model));
	}

	@Override
	public boolean performFinish() {
		try {
			TablixXml xml = new TablixFAO().importFrom(model.getFile());
			TablixDataProvider dp = new TablixDataProvider(xml, model.getFile());
			ConfettiPlugin.getDefault().setDataProvider(dp, dp);
			return true;
		} catch (FAOException e) {
			e.printStackTrace();
			return true;
		}
	}

}
