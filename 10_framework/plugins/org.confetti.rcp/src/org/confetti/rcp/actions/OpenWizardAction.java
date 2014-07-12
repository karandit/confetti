package org.confetti.rcp.actions;

import java.util.List;

import org.confetti.rcp.extensions.OpenWizardDescr;
import org.confetti.rcp.extensions.OpenWizardRegistry;
import org.confetti.rcp.wizards.OpenWizard;
import org.confetti.rcp.wizards.OpenWizardModel;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class OpenWizardAction extends Action {

	public OpenWizardAction() {
		super();
		setId("openWizard");
		setText("Open...");
	}

	@Override
	public void run() {
		List<OpenWizardDescr> extensions = OpenWizardRegistry.INSTANCE.getExtensions();
		
		OpenWizardModel model = new OpenWizardModel(extensions);
		IWizard wizard = new OpenWizard(model);
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();
	}

	
}
