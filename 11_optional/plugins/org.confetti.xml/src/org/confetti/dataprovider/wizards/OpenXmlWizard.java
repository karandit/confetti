package org.confetti.dataprovider.wizards;

import org.confetti.dataprovider.xml.XmlDataProvider;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.xml.FAOException;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.PlatformUI;

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
			ConfettiPlugin.getDefault().setDataProvider(new XmlDataProvider(model.getFile()));
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().setText("Confetti - FET");
		} catch (FAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}