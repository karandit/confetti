package org.confetti.rcp.views;

import static org.confetti.rcp.views.AssignmentsView.getName;
import static org.confetti.rcp.views.AssignmentsView.toStr;

import org.confetti.core.Assignment;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

class AssignmentLabelProvider extends LabelProvider implements ITableLabelProvider, ITableColorProvider {

	private final Display display;

	public AssignmentLabelProvider(Display display) {
		this.display = display;
	}
	
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

	@Override public Color getForeground(Object element, int columnIndex) { return null; }

	@Override
	public Color getBackground(Object element, int columnIndex) {
		Assignment assignment = (Assignment) element;
		switch (columnIndex) {
			case 1:  	return display.getSystemColor(assignment.getSubject().getColor());
			default: 	return null;
		}
	}

}
