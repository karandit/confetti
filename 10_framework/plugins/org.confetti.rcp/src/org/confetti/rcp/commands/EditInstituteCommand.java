package org.confetti.rcp.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public class EditInstituteCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
	    if (selection == null || selection.isEmpty()
	    		|| !(selection instanceof IStructuredSelection) ) {
	    	return null;
	    }
//        IStructuredSelection strucSelection = (IStructuredSelection) selection;
        Shell shell = Display.getDefault().getActiveShell();
        MessageDialog.openInformation(shell, "Info", "Not implemented yet.");
	    return null;
	}

}
