package org.confetti.dataprovider.wizards;

import org.confetti.dataprovider.xml.XmlDataProvider;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.xml.FAOException;
import org.confetti.xml.InstituteFAO;
import org.confetti.xml.core.InstituteXml;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Bubla Gabor
 */
public class OpenXmlWizard extends Wizard {

	private final OpenXmlWizardModel model = new OpenXmlWizardModel();
	
	@Override
	public void addPages() {
		setWindowTitle("Open");
		addPage(new ChooseFileWizardPage(model));
	}

	@Override
	public boolean performFinish() {
		try {
			InstituteXml  inst =  new InstituteFAO().importFrom(model.getFile());
			XmlDataProvider dp = new XmlDataProvider(inst, model.getFile());
            ConfettiPlugin.getDefault().setDataProvider(dp, dp);
		} catch (FAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}