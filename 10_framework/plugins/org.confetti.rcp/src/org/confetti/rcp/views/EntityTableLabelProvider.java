package org.confetti.rcp.views;

import org.confetti.core.Entity;
import org.confetti.core.Nameable;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class EntityTableLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override public Image getColumnImage(Object element, int columnIndex) { return null; }
	
	@Override
	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof Nameable) {
			return getTextByColumn(columnIndex, ((Nameable) element).getName().getValue());
		} else if (element instanceof Entity) {
			return getTextByColumn(columnIndex, ((Entity) element).getName());
		} 
		return null;
	}

	private String getTextByColumn(int columnIndex, String name) {
		switch (columnIndex) {
		case 0: return name;
		case 1: return "1/4";
		default: return "";
		}
	}
}
