package org.confetti.rcp.commands;

import static org.confetti.rcp.commands.AbstractNewEntityHandler.isWritable;
import static org.confetti.rcp.wizards.WizardUtil.watchWizardDialog;

import org.confetti.core.DataPersister;
import org.confetti.core.DataProvider;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.wizards.AssignmentWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class NewAssignmentCommand extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        DataProvider dataProvider = ConfettiPlugin.getDefault().getDataProvider().getValue();
        DataPersister dataPersister = ConfettiPlugin.getDefault().getDataPersister().get();
        
        AssignmentWizard wizard = new AssignmentWizard(dataProvider, dataPersister);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        watchWizardDialog(dialog);
        dialog.open();

        return null;
    }

    @Override public boolean isEnabled() { return isWritable(); }
    
}
