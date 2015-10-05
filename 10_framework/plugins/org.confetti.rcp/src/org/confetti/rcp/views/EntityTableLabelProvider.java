package org.confetti.rcp.views;

import static org.confetti.rcp.ConfettiPlugin.IMG_SMALL_BUILDING;
import static org.confetti.rcp.ConfettiPlugin.IMG_SMALL_ROOM;
import static org.confetti.rcp.ConfettiPlugin.IMG_SMALL_STUDENTGROUP;
import static org.confetti.rcp.ConfettiPlugin.IMG_SMALL_SUBJECT;
import static org.confetti.rcp.ConfettiPlugin.IMG_SMALL_TAG;
import static org.confetti.rcp.ConfettiPlugin.IMG_SMALL_TEACHER;

import org.confetti.core.Assignable;
import org.confetti.core.Building;
import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.Nameable;
import org.confetti.core.NameableVisitee;
import org.confetti.core.NameableVisitor;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Tag;
import org.confetti.core.Teacher;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.google.common.collect.Iterables;

public class EntityTableLabelProvider extends LabelProvider implements ITableLabelProvider {

	private enum GetImageKeyVisitor implements NameableVisitor<String, Void> {
		
		INSTANCE;

		@Override public String visitDay(Day day, Void p) 					{ return null; }
		@Override public String visitHour(Hour hour, Void p) 				{ return null; }
		@Override public String visitSubject(Subject subject, Void p) 		{ return IMG_SMALL_SUBJECT; }
		@Override public String visitTeacher(Teacher teacher, Void p) 		{ return IMG_SMALL_TEACHER; }
		@Override public String visitStudentGroup(StudentGroup sG, Void p)  { return IMG_SMALL_STUDENTGROUP; }
		@Override public String visitRoom(Room room, Void p) 				{ return IMG_SMALL_ROOM; }
		@Override public String visitTag(Tag tag, Void p) 					{ return IMG_SMALL_TAG; }
		@Override public String visitBuilding(Building building, Void p) 	{ return IMG_SMALL_BUILDING; }
	}
	
	@Override public Image getColumnImage(Object element, int columnIndex) { 
		ImageRegistry imageRegistry = ConfettiPlugin.getDefault().getImageRegistry();
		switch (columnIndex) {
		case 0:  	return imageRegistry.get(getImageKey(element));
		default: 	return null;
		}
	}

	private String getImageKey(Object element) {
		if (element instanceof NameableVisitee) {
			return ((NameableVisitee) element).accept(GetImageKeyVisitor.INSTANCE, null);
		}
		return null;
	}
	 	
	@Override
	public String getColumnText(Object element, int columnIndex) {
		switch (columnIndex) {
			case 0: return element instanceof Nameable 
					? ((Nameable) element).getName().getValue() 
					: ""; //$NON-NLS-1$
			case 1: return element instanceof Assignable 
					? Integer.toString(Iterables.size(((Assignable) element).getAssignments().getList())) 
					: ""; //$NON-NLS-1$
			default: return ""; //$NON-NLS-1$
		}
	}
}
