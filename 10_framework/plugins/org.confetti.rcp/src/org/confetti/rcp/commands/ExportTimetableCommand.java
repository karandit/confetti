package org.confetti.rcp.commands;

import org.confetti.core.DataProvider;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.nls.Messages;
import org.confetti.rcp.wizards.ExportTimetableWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

/**
 * @author Gabor Bubla
 */
public class ExportTimetableCommand extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), new ExportTimetableWizard());
        wizardDialog.setTitle(Messages.ExportTimetableCommand_Title);
        wizardDialog.open();
        return null;
    }

    @Override
    public boolean isEnabled() {
        DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
        if (dp != null && dp.getSolution().getValue() != null) {
            return true;
        }
        return false;
    }
    
}
