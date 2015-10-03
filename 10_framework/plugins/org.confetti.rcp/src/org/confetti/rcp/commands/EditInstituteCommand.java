package org.confetti.rcp.commands;

import static org.confetti.rcp.commands.AbstractNewEntityHandler.isWritable;

import org.confetti.core.DataProvider;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.wizards.EditInstituteWizard;
import org.confetti.rcp.wizards.models.EditInstituteModel;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public class EditInstituteCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
	    if (selection == null || selection.isEmpty() || !(selection instanceof IStructuredSelection) ) {
	    	return null;
	    }
        Shell shell = Display.getDefault().getActiveShell();
		ConfettiPlugin confettiActvator = ConfettiPlugin.getDefault();
		final DataProvider dp = confettiActvator.getDataProvider().getValue();
        
		EditInstituteModel model = new EditInstituteModel(dp.getName().getValue(), dp.getComment().getValue());
        WizardDialog dialog = new WizardDialog(shell, new EditInstituteWizard(model));
        if (Window.OK != dialog.open()) {
            return null;
        }
        confettiActvator.getDataPersister().get().updateInstituteNameAndComment(model.getName(), model.getComment());
	    return null;
	}

	@Override public boolean isEnabled() { return isWritable(); }
}
