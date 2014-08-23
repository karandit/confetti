package org.confetti.rcp.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author Gabor Bubla
 */
public class RenameEntityCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
	    if (selection != null & selection instanceof IStructuredSelection) {
	      IStructuredSelection strucSelection = (IStructuredSelection) selection;
//	      for (Iterator<ObservableValue<Entity>> iterator = strucSelection.iterator(); iterator.hasNext();) {
//	        ObservableValue<Entity> element = iterator.next();
//	        System.out.println(element.getValue().getName());
//	      }
	    }
	    
	    InputDialog inputDialog = new InputDialog(Display.getDefault().getActiveShell(), "Rename", "Rename", "", new IInputValidator() {
			@Override
			public String isValid(String newText) {
				//TODO check and only return null if newText is a unique name
//				if (newText.isUnique()) {
//					return null;
//				}
				return "New name is not unique!";
			}
		});
	    inputDialog.open();
	    
		return null;
	}

}
