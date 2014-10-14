package org.confetti.rcp.commands;

import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.dialogs.AddAssignmentDialog;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.PlatformUI;

public class NewAssignmentCommand extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Dialog addAssignmentDialog = new AddAssignmentDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        addAssignmentDialog.open();
        
        return null;
    }

    @Override public boolean isEnabled() { return ConfettiPlugin.getDefault().getDataProvider().getValue() == null ? false : true; }
    
}
