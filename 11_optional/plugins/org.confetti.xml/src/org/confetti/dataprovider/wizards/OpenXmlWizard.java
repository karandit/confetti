package org.confetti.dataprovider.wizards;

import org.confetti.dataprovider.xml.XmlDataProvider;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Bubla Gabor
 */
public class OpenXmlWizard extends Wizard {

	private final OpenXmlWizardModel model = new OpenXmlWizardModel();
	
	@Override
	public void addPages() {
		addPage(new ChooseFileWizardPage(model));
	}

	@Override
	public boolean performFinish() {
		ConfettiPlugin.getDefault().setDataProvider(new XmlDataProvider(model.getFile()));
		return true;
	}

}