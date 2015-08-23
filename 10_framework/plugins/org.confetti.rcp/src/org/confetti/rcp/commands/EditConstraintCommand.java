package org.confetti.rcp.commands;

import org.confetti.core.Constraint;
import org.confetti.core.ConstraintAttributes;
import org.confetti.core.DataProvider;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.constraints.ConstraintDialog;
import org.confetti.rcp.extensions.ConstraintDescr;
import org.confetti.rcp.extensions.ConstraintRegistry;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author Kárándi Tamás
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
		ConstraintAttributes newAttrs = new ConstraintAttributes(constraint.getAttributes().getValue());
		ConstraintDialog constraintDialog = new ConstraintDialog(shell, constraintDescr, newAttrs);
        if (Window.OK != constraintDialog.open()) {
        	return null;
        }
		final DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
		dp.updateConstraint(constraint, newAttrs);

	    return null;
	}

}
