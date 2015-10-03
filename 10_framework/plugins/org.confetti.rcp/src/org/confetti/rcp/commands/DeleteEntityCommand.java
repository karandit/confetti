package org.confetti.rcp.commands;

import static com.google.common.collect.Iterables.isEmpty;
import static java.util.Arrays.asList;
import static org.confetti.rcp.commands.AbstractNewEntityHandler.isWritable;

import org.confetti.core.DataPersister;
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
	    if (selection != null && !selection.isEmpty() && selection instanceof IStructuredSelection) {
	        IStructuredSelection strucSelection = (IStructuredSelection) selection;
	        final Entity firstSelected = (Entity) strucSelection.getFirstElement();
            if (MessageDialog.openConfirm(shell, "Delete", "The selected Entity will be deleted! \n Are you sure?")) {
	        	DataPersister dataPersister = ConfettiPlugin.getDefault().getDataPersister().get();;
            	if (!firstSelected.accept(DeleteEntityVisitor.INSTANCE, dataPersister)) {
	        	    MessageDialog.openError(shell, "Delete", 
	        	    		"The selected Entity could not be deleted, because it has Assignments. \n"
	        	            + "Please delete it's assignments in the Assignments view first!");
	        	}
	        }
	    }
	    return null;
	}

	@Override public boolean isEnabled() { return isWritable(); }
	
	private enum DeleteEntityVisitor implements EntityVisitor<Boolean, DataPersister> {
        
	    INSTANCE;

        @Override
        public Boolean visitSubject(Subject subject, DataPersister dp) {
            if (isEmpty(subject.getAssignments().getList())) {
                dp.removeSubjects(asList(subject));
                return true;
            }
            return false;
        }
        @Override
        public Boolean visitTeacher(Teacher teacher, DataPersister dp) {
            if (isEmpty(teacher.getAssignments().getList())) {
                dp.removeTeachers(asList(teacher));
                return true;
            }
            return false;
        }
        @Override
        public Boolean visitStudentGroup(StudentGroup studentGroup, DataPersister dp) {
            if (isEmpty(studentGroup.getAssignments().getList())) {
                dp.removeStudentGroups(asList(studentGroup));
                return true;
            }
            return false;
        }
        @Override
        public Boolean visitRoom(Room room, DataPersister dp) {
            if (isEmpty(room.getAssignments().getList())) {
                dp.removeRooms(asList(room));
                return true;
            }
            return false;
        }
	}

}
