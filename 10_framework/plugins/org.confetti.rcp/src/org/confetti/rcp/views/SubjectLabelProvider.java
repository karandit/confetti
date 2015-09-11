package org.confetti.rcp.views;

import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class SubjectLabelProvider extends EntityTableLabelProvider implements ITableColorProvider {

	private final Display display;

	public SubjectLabelProvider(Display display) {
		this.display = display;
	}

	@Override public Color getForeground(Object element, int columnIndex) { return null; }

	@Override
	public Color getBackground(Object element, int columnIndex) {
		switch (columnIndex) {
		case 0:  	return display.getSystemColor(SWT.COLOR_CYAN);
		default: 	return null;
		}
	}


}
