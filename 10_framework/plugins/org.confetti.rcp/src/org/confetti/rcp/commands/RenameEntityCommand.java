package org.confetti.rcp.commands;

import java.util.HashSet;
import java.util.Set;

import org.confetti.core.DataProvider;
import org.confetti.core.Entity;
import org.confetti.core.EntityVisitor;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.observable.ObservableList;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author Gabor Bubla
 */
public class RenameEntityCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPage activePage = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage();
		ISelection selection = activePage.getSelection();
	    if (selection == null || selection.isEmpty() || !(selection instanceof IStructuredSelection)) {
	    	return null;
	    }
		IStructuredSelection strucSelection = (IStructuredSelection) selection;
		final Entity sel = (Entity) strucSelection.getFirstElement();
		InputDialog inputDialog = new InputDialog(Display.getDefault().getActiveShell(), "Rename", "Please enter a new name", sel.getName().getValue(), 
				new IInputValidator() {
					@Override
					public String isValid(String newText) {
						if (newText.isEmpty()) {
							return "Name must be at least 1 character!";
						}
						if (!isUnique(sel, newText)) {
							return "New name is not unique!";
						}
						return null;
					}
				});
		int button = inputDialog.open();
		if (button == Window.OK) {
			DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
			dp.rename(sel, inputDialog.getValue());
		}
		return null;
	}

	protected boolean isUnique(Entity sel, String newText) {
		DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
		Set<String> allNames = sel.accept(GetAllNamesVisitor.INSTANCE, dp);
		return !allNames.contains(newText);
	}

	private enum GetAllNamesVisitor implements EntityVisitor<Set<String>, DataProvider> {

		INSTANCE;
		
		@Override public Set<String> visitSubject(Subject subject, DataProvider dp) 				{ return getNames(dp.getSubjects()); }
		@Override public Set<String> visitTeacher(Teacher teacher, DataProvider dp) 				{ return getNames(dp.getTeachers()); }
		@Override public Set<String> visitRoom(Room room, DataProvider dp) 							{ return getNames(dp.getRooms()); }
		@Override public Set<String> visitStudentGroup(StudentGroup studentGroup, DataProvider dp) 	{ return collectNames(dp.getStudentGroups().getList()); }
		
		private Set<String> getNames(ObservableList<? extends Entity> entities) {
			Set<String> names = new HashSet<>();
			for (Entity entity : entities.getList()) {
				names.add(entity.getName().getValue());
			}
			return names;
		}

		private Set<String> collectNames(Iterable<StudentGroup> groups) {
			Set<String> names = new HashSet<>();
			for (StudentGroup studentGroup : groups) {
				names.add(studentGroup.getName().getValue());
				names.addAll(collectNames(studentGroup.getChildren()));
			}
			return names;
		}

	}
	
}
