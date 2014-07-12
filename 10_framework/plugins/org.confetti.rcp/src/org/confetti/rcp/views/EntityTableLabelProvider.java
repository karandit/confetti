package org.confetti.rcp.views;

import org.confetti.core.Entity;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class EntityTableLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override public Image getColumnImage(Object element, int columnIndex) { return null; }
	
	@Override
	public String getColumnText(Object element, int columnIndex) {
		Entity entity = (Entity) element;
		switch (columnIndex) {
		case 0: return "1/4";
		default: return entity.getName();
		}
	}
}
