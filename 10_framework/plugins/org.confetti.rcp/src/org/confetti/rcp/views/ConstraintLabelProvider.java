package org.confetti.rcp.views;

import static com.google.common.collect.Lists.transform;
import static java.lang.String.format;
import static java.lang.String.join;
import static org.confetti.rcp.constraints.FieldTypePrettyPrintVisitor.PRETTY_PRINT;

import org.confetti.core.Constraint;
import org.confetti.core.ConstraintAttributes;
import org.confetti.rcp.extensions.ConstraintDescr;
import org.confetti.rcp.extensions.ConstraintRegistry;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

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
				return 
					join("; ", 
					transform(constraintDescr.getFields(), field -> 
					format("%s = %s", field.getLabel(), field.getType().accept(PRETTY_PRINT, field.getName(), attrs))));
			default: return "";
		}
	}
}
