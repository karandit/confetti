package org.confetti.rcp.views;

import static org.confetti.rcp.views.AssignmentsView.getName;
import static org.confetti.rcp.views.AssignmentsView.toStr;

import org.confetti.core.Assignment;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

class AssignmentLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override public Image getColumnImage(Object element, int columnIndex) { return null; }

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Assignment assignment = (Assignment) element;
		switch (columnIndex) {
			case 0: return "";
			case 1:	return getName(assignment.getSubject());
			case 2:	return toStr(assignment.getTeachers().getList());
			case 3:	return toStr(assignment.getStudentGroups().getList());
			default : return getName(assignment.getRoom());
		}
	}

}
