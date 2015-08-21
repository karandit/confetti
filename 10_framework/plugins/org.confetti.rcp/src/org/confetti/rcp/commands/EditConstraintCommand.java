package org.confetti.rcp.commands;

import org.confetti.core.Constraint;
import org.confetti.core.ConstraintAttributes;
import org.confetti.rcp.constraints.ConstraintDialog;
import org.confetti.rcp.extensions.ConstraintDescr;
import org.confetti.rcp.extensions.ConstraintRegistry;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author K�r�ndi Tam�s
 */
public class EditConstraintCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
	    if (selection == null || selection.isEmpty()
	    		|| !(selection instanceof IStructuredSelection) ) {
	    	return null;
	    }
        IStructuredSelection strucSelection = (IStructuredSelection) selection;
        Constraint constraint = (Constraint) strucSelection.getFirstElement();
        Shell shell = Display.getDefault().getActiveShell();
        ConstraintRegistry reg = ConstraintRegistry.INSTANCE;
		ConstraintDescr constraintDescr = reg.getConstraintDescrById(constraint.getConstraintType());
		ConstraintAttributes attrs = constraint.getAttributes();
		ConstraintDialog constraintDialog = new ConstraintDialog(shell, constraintDescr, attrs);
		constraintDialog.open();
        
	    return null;
	}

}
