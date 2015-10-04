package org.confetti.rcp.commands;

import static org.confetti.rcp.wizards.WizardUtil.watchWizardDialog;

import org.confetti.core.DataPersister;
import org.confetti.core.DataProvider;
import org.confetti.core.Nameable;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.wizards.NewEntityWizard;
import org.confetti.rcp.wizards.models.NewEntityWizardModel;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

abstract class AbstractNewEntityHandler<T extends Nameable> extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ConfettiPlugin plugin = ConfettiPlugin.getDefault();
		DataProvider dataProvider = plugin.getDataProvider().getValue();
		DataPersister dataPersister = plugin.getDataPersister().get();

		IWizard wizard = new NewEntityWizard(createModel(dataProvider, dataPersister));
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		WizardDialog dialog = new WizardDialog(shell, wizard);
		watchWizardDialog(dialog);
		dialog.open();
		return null;
	}

	protected abstract NewEntityWizardModel<T> createModel(DataProvider dataProvider, DataPersister dataPersister);

	@Override public boolean isEnabled() { return isWritable(); }

	@Override public void dispose() { }
	
	static boolean isWritable() { return ConfettiPlugin.getDefault().getDataPersister().isPresent(); }
	
}
