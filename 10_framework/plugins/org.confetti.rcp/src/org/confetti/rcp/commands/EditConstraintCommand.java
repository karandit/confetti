package org.confetti.rcp.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class EditConstraintCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
	    Shell shell = Display.getDefault().getActiveShell();
	    MessageDialog.openInformation(shell, "Warning", "not implemented yet.");
//		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
//	    if (!selection.toString().equals("<empty selection>") 
//	    		&& selection != null 
//	    		&& selection instanceof IStructuredSelection) {
//	        IStructuredSelection strucSelection = (IStructuredSelection) selection;
//	        final Entity firstSelected = (Entity) strucSelection.getFirstElement();
//            if (MessageDialog.openConfirm(shell, "Delete", "The selected Entity will be deleted! \n Are you sure?")) {
//	        	if (firstSelected.accept(DeleteEntityVisitor.INSTANCE, null)) {
//	        	    //deleted succesfully
//	        	} else {
//	        	    MessageDialog.openError(shell, "Delete", "The selected Entity could not be deleted, because it has Assignments. \n"
//	        	            + "Please delete it's assignments in the Assignments view first!");
//	        	}
//	        }
//	    }
	    return null;
	}

}
