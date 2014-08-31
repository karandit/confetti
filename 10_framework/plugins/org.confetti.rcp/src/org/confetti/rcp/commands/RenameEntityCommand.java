package org.confetti.rcp.commands;

import java.util.Iterator;

import org.confetti.core.Entity;
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
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author Gabor Bubla
 */
public class RenameEntityCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
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
	    		  } else if (!isUnique(sel, newText)) {
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

	protected boolean isUnique(Entity sel, String newText) {
		//TODO fix this mess
		if (sel instanceof Subject) {
			ObservableList<Subject> entities = ConfettiPlugin.getDefault().getDataProvider().getValue().getSubjects();
			for (Iterator<Subject> iterator = entities.getList().iterator(); iterator.hasNext();) {
				Subject element = iterator.next();
				if (element.getName().getValue().equals(newText)) {
					return false;
				}
			}
			return true;
		} else if (sel instanceof Teacher) {
			ObservableList<Teacher> entities = ConfettiPlugin.getDefault().getDataProvider().getValue().getTeachers();
			for (Iterator<Teacher> iterator = entities.getList().iterator(); iterator.hasNext();) {
				Teacher element = iterator.next();
				if (element.getName().getValue().equals(newText)) {
					return false;
				}
			}
			return true;
		} else if (sel instanceof StudentGroup) {
			ObservableList<StudentGroup> entities = ConfettiPlugin.getDefault().getDataProvider().getValue().getStudentGroups();
			for (Iterator<StudentGroup> iterator = entities.getList().iterator(); iterator.hasNext();) {
				StudentGroup element = iterator.next();
				if (element.getName().getValue().equals(newText)) {
					return false;
				}
			}
			return true;
		} else if (sel instanceof Room) {
			ObservableList<Room> entities = ConfettiPlugin.getDefault().getDataProvider().getValue().getRooms();
			for (Iterator<Room> iterator = entities.getList().iterator(); iterator.hasNext();) {
				Room element = iterator.next();
				if (element.getName().getValue().equals(newText)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	private void changeName(Entity sel, String value) {
		ConfettiPlugin.getDefault().getDataProvider().getValue().rename(sel, value);
	}

}
