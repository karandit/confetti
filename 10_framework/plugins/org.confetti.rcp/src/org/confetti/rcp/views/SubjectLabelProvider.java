package org.confetti.rcp.views;

import org.confetti.core.Subject;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.swt.graphics.Color;

public class SubjectLabelProvider extends EntityTableLabelProvider implements ITableColorProvider {

	@Override public Color getForeground(Object element, int columnIndex) { return null; }

	@Override
	public Color getBackground(Object element, int columnIndex) {
		switch (columnIndex) {
		case 0:  	return ColorCache.INSTANCE.getColor(((Subject) element).getColor());
		default: 	return null;
		}
	}

}
