package org.confetti.rcp.views;

import org.confetti.core.Constraint;
import org.confetti.core.ConstraintAttributes;
import org.confetti.rcp.extensions.ConstraintDescr;
import org.confetti.rcp.extensions.ConstraintRegistry;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.google.common.collect.Lists;

class ConstraintLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override public Image getColumnImage(Object element, int columnIndex) { return null; }

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Constraint constraint = (Constraint) element;
		ConstraintRegistry reg = ConstraintRegistry.INSTANCE;
		ConstraintDescr constraintDescr = reg.getConstraintDescrById(constraint.getConstraintType());
		if (constraintDescr == null) {
			return "";
		}
		
		switch (columnIndex) {
			case 0: 
				return constraintDescr.getName();
			case 1: 
				ConstraintAttributes attrs = constraint.getAttributes().getValue();
				return 	String.join("; ", 
						Lists.transform(constraintDescr.getFields(), field -> 
						String.format("%s = %s", field.getLabel(), field.printValue(attrs, constraint))));
			default: return "";
		}
	}
}
