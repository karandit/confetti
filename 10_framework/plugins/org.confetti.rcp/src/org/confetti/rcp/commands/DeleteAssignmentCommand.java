package org.confetti.rcp.commands;

import org.confetti.core.Assignment;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author Gabor Bubla
 */
public class DeleteAssignmentCommand extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Shell shell = Display.getDefault().getActiveShell();
        
        ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
        if (!selection.toString().equals("<empty selection>") && selection != null && selection instanceof IStructuredSelection) {
            IStructuredSelection strucSelection = (IStructuredSelection) selection;
            final Assignment selectedAssignment = (Assignment) strucSelection.getFirstElement();
            
            if (MessageDialog.openConfirm(shell, "Delete", "The selected Assignment will be deleted! \n Are you sure?")) {
                ConfettiPlugin.getDefault().getDataProvider().getValue().removeAssignment(selectedAssignment);
            }
        }
        
        return null;
    }

}
