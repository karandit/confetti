package org.confetti.rcp.commands;

import org.confetti.core.Entity;
import org.confetti.core.EntityVisitor;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
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
	        final Entity firstSelected = (Entity) strucSelection.getFirstElement();
	        if (MessageDialog.openConfirm(Display.getDefault().getActiveShell(), "Delete", "The selected Entities will be deleted! \n Are you sure?")) {
	        	firstSelected.accept(DeleteEntityVisitor.INSTANCE, null);
	        }
	    }
	    return null;
	}

	private enum DeleteEntityVisitor implements EntityVisitor<Boolean, Void> {
        
	    INSTANCE;

        @Override
        public Boolean visitSubject(Subject subject, Void param) {
            ConfettiPlugin.getDefault().getDataProvider().getValue().removeSubject(subject);
            return null;
        }
        @Override
        public Boolean visitTeacher(Teacher teacher, Void param) {
            ConfettiPlugin.getDefault().getDataProvider().getValue().removeTeacher(teacher);
            return null;
        }
        @Override
        public Boolean visitStudentGroup(StudentGroup studentGroup, Void param) {
            ConfettiPlugin.getDefault().getDataProvider().getValue().removeStudentGroup(studentGroup);
            return null;
        }
        @Override
        public Boolean visitRoom(Room room, Void param) {
            ConfettiPlugin.getDefault().getDataProvider().getValue().removeRoom(room);
            return null;
        }
	}

}
