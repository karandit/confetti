package org.confetti.rcp.actions;

import org.confetti.rcp.wizards.OpenWizard;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class OpenWizardAction extends Action {

	public OpenWizardAction() {
		super();
		setText("macska");
		setId("doge");
	}

	@Override
	public void run() {
		IWizard wizard = new OpenWizard();
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();
	}

	
}
