package org.confetti.rcp.actions;

import org.eclipse.jface.action.Action;

public class NewWizardAction extends Action {

	public NewWizardAction() {
		super();
		setId("newWizard");
		setText("New...");
	}

	@Override
	public void run() {
//		List<OpenWizardDescr> extensions = OpenWizardRegistry.INSTANCE.getExtensions();
//		
//		OpenWizardModel model = new OpenWizardModel(extensions);
//		IWizard wizard = new OpenWizard(model);
//		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
//		WizardDialog dialog = new WizardDialog(shell, wizard);
//		dialog.open();
	}
}
