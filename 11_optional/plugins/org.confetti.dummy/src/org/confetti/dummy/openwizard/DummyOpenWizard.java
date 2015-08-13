package org.confetti.dummy.openwizard;

import org.confetti.dummy.DummyDataProvider;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.PlatformUI;

public class DummyOpenWizard extends Wizard {

	public DummyOpenWizard() {
		//TODO: create a model here
		super();
		setWindowTitle("Open");
	}

	@Override
	public void addPages() {
		addPage(new ChooseFileWizardPage());
	}

	@Override
	public boolean performFinish() {
	    DummyDataProvider dp = new DummyDataProvider();
        ConfettiPlugin.getDefault().setDataProvider(dp, dp);
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().setText("Confetti - Dummy");
		return true;
	}

}
