package org.confetti.dummy.openwizard;

import org.confetti.core.DataProvider;
import org.confetti.dummy.DataProviderImpl;
import org.confetti.observable.ObservableValue;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.wizard.Wizard;

public class DummyOpenWizard extends Wizard {

	ChooseFileWizardPage chooseFile;
	
	public DummyOpenWizard() {
		super();
		chooseFile = new ChooseFileWizardPage();
	}

	@Override
	public void addPages() {
		addPage(chooseFile);
	}

	@Override
	public boolean performFinish() {
		//TODO:Open the selected file with
		//chooseFile.getPath();
		ConfettiPlugin plugin = ConfettiPlugin.getDefault();
		ObservableValue<DataProvider> impl = new ObservableValue<>();
		DataProvider dp = new DataProviderImpl();
		impl.setValue(dp);
		plugin.setDp(impl);
		return true;
	}

}
