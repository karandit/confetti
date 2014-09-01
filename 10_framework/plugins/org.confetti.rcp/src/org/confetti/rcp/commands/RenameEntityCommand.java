package org.confetti.rcp.commands;

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
		final String viewId = activePage.getActivePart().getSite().getId();
		ISelection selection = activePage.getSelection();
	    if (!selection.toString().equals("<empty selection>") && selection != null && selection instanceof IStructuredSelection) {
	      IStructuredSelection strucSelection = (IStructuredSelection) selection;
//	      for (Iterator<ObservableValue<Entity>> iterator = strucSelection.iterator(); iterator.hasNext();) {
//	    	  ObservableValue<Entity> element = iterator.next();
//	    	  System.out.println(element.getValue().getName());
//	      }
	      final Entity sel = (Entity) strucSelection.getFirstElement();
	      InputDialog inputDialog = new InputDialog(Display.getDefault().getActiveShell(), "Rename", "Please enter a new name", sel.getName().getValue(), new IInputValidator() {
	    	  @Override
	    	  public String isValid(String newText) {
	    		  if (newText.isEmpty()) {
	    			  return "Name must be at least 1 character!";
	    		  } else if (!isUnique(viewId, sel, newText)) {
	    			  return "New name is not unique!";
	    		  }
	    		  return null;
	    	  }
	      });
	      int button = inputDialog.open();
	      if (button == Window.OK) {
	    	  changeName(sel, inputDialog.getValue());
	      }
	    }
	    
		return null;
	}

	protected boolean isUnique(String viewId, Entity sel, String newText) {
		DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
		ObservableList<? extends Entity> allEntities = sel.accept(GetAllVisitor.INSTANCE, dp);
		if (allEntities == null) {
			return false;
		}
		return containsName(newText, allEntities.getList());
	}

	private boolean containsName(String newText, Iterable<? extends Entity> list) {
		for (Entity element : list) {
			if (newText.equals(element.getName().getValue())) {
				return false;
			}
		}
		return true;
	}
	
	private void changeName(Entity sel, String value) {
		ConfettiPlugin.getDefault().getDataProvider().getValue().rename(sel, value);
	}

	private enum GetAllVisitor implements EntityVisitor<ObservableList<? extends Entity>, DataProvider> {

		INSTANCE;
		
		@Override
		public ObservableList<? extends Entity> visitSubject(Subject subject, DataProvider dp) {
			return dp.getSubjects();
		}

		@Override
		public ObservableList<? extends Entity> visitTeacher(Teacher teacher, DataProvider dp) {
			return dp.getTeachers();
		}

		@Override
		public ObservableList<? extends Entity> visitStudentGroup(StudentGroup studentGroup, DataProvider dp) {
			return dp.getStudentGroups();
		}

		@Override
		public ObservableList<? extends Entity> visitRoom(Room room, DataProvider dp) {
			return dp.getRooms();
		}
		
	}
	
}
