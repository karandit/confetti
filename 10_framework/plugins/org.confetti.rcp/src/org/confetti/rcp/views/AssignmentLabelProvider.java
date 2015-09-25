package org.confetti.rcp.views;

import static org.confetti.rcp.views.AssignmentsView.getName;
import static org.confetti.rcp.views.AssignmentsView.toStr;

import org.confetti.core.Assignment;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

class AssignmentLabelProvider extends LabelProvider implements ITableLabelProvider, ITableColorProvider {

	@Override public Image getColumnImage(Object element, int columnIndex) { return null; }

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Assignment assignment = (Assignment) element;
		switch (columnIndex) {
			case 1:	return assignment.getDuration().getValue().toString();
			case 2:	return getName(assignment.getSubject());
			case 3:	return toStr(assignment.getTeachers().getList());
			case 4:	return toStr(assignment.getStudentGroups().getList());
			case 5:	return toStr(assignment.getTags().getList());
			default : return ""; //$NON-NLS-1$
		}
	}

	@Override public Color getForeground(Object element, int columnIndex) { return null; }

	@Override
	public Color getBackground(Object element, int columnIndex) {
		Assignment assignment = (Assignment) element;
		switch (columnIndex) {
			case 2:  	return ColorCache.INSTANCE.getColor(assignment.getSubject().getColor());
			default: 	return null;
		}
	}

}
