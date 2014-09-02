package org.confetti.rcp.commands;

import org.confetti.core.Entity;
import org.confetti.core.Room;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author Gabor Bubla
 */
public class DeleteEntityCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
	    if (!selection.toString().equals("<empty selection>") && selection != null && selection instanceof IStructuredSelection) {
	        IStructuredSelection strucSelection = (IStructuredSelection) selection;
//	        for (Iterator<ObservableValue<Entity>> iterator = strucSelection.iterator(); iterator.hasNext();) {
//	        	ObservableValue<Entity> element = iterator.next();
//	        	System.out.println(element.getValue().getName());
//	        }
	        final Entity sel = (Entity) strucSelection.getFirstElement();
	        if (MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Delete", "The selected Entities will be deleted! \n Are you sure?")) {
	    	    //delete the entity
	        	delete(sel);
	        }
	    }
	    return null;
	}

	private void delete(Entity sel) {
		if (sel instanceof Subject) {
			ConfettiPlugin.getDefault().getDataProvider().getValue().removeSubject((Subject) sel);
		} else if (sel instanceof Teacher) {
			ConfettiPlugin.getDefault().getDataProvider().getValue().removeTeacher((Teacher) sel);
//		} else if (sel instanceof StudentGroup) {
//			ConfettiPlugin.getDefault().getDataProvider().getValue().removeStudentGroup((StudentGroup) sel);
		} else if (sel instanceof Room) {
			ConfettiPlugin.getDefault().getDataProvider().getValue().removeRoom((Room) sel);
		}
	}

}
