package org.confetti.rcp.commands;

import static com.google.common.collect.Iterables.isEmpty;
import static java.util.Arrays.asList;
import static org.confetti.rcp.commands.AbstractNewEntityHandler.isWritable;

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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author Gabor Bubla
 */
public class DeleteEntityCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
	    Shell shell = Display.getDefault().getActiveShell();
	    
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
	    if (!selection.toString().equals("<empty selection>") && selection != null && selection instanceof IStructuredSelection) {
	        IStructuredSelection strucSelection = (IStructuredSelection) selection;
//	        for (Iterator<ObservableValue<Entity>> iterator = strucSelection.iterator(); iterator.hasNext();) {
//	        	ObservableValue<Entity> element = iterator.next();
//	        	System.out.println(element.getValue().getName());
//	        }
	        final Entity firstSelected = (Entity) strucSelection.getFirstElement();
            if (MessageDialog.openConfirm(shell, "Delete", "The selected Entity will be deleted! \n Are you sure?")) {
	        	if (firstSelected.accept(DeleteEntityVisitor.INSTANCE, null)) {
	        	    //deleted succesfully
	        	} else {
	        	    MessageDialog.openError(shell, "Delete", "The selected Entity could not be deleted, because it has Assignments. \n"
	        	            + "Please delete it's assignments in the Assignments view first!");
	        	}
	        }
	    }
	    return null;
	}

	@Override public boolean isEnabled() { return isWritable(); }
	
	private enum DeleteEntityVisitor implements EntityVisitor<Boolean, Void> {
        
	    INSTANCE;

        @Override
        public Boolean visitSubject(Subject subject, Void param) {
            if (isEmpty(subject.getAssignments().getList())) {
                ConfettiPlugin.getDefault().getDataProvider().getValue().removeSubjects(asList(subject));
                return true;
            }
            return false;
        }
        @Override
        public Boolean visitTeacher(Teacher teacher, Void param) {
            if (isEmpty(teacher.getAssignments().getList())) {
                ConfettiPlugin.getDefault().getDataProvider().getValue().removeTeachers(asList(teacher));
                return true;
            }
            return false;
        }
        @Override
        public Boolean visitStudentGroup(StudentGroup studentGroup, Void param) {
            if (isEmpty(studentGroup.getAssignments().getList())) {
                ConfettiPlugin.getDefault().getDataProvider().getValue().removeStudentGroups(asList(studentGroup));
                return true;
            }
            return false;
        }
        @Override
        public Boolean visitRoom(Room room, Void param) {
            if (isEmpty(room.getAssignments().getList())) {
                ConfettiPlugin.getDefault().getDataProvider().getValue().removeRooms(asList(room));
                return true;
            }
            return false;
        }
	}

}
