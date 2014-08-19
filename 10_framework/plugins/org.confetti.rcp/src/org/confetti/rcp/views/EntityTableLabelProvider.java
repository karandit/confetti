package org.confetti.rcp.views;

import org.confetti.core.Nameable;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class EntityTableLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override public Image getColumnImage(Object element, int columnIndex) { 
		ImageRegistry imageRegistry = ConfettiPlugin.getDefault().getImageRegistry();
		switch (columnIndex) {
		case 0:  	return imageRegistry.get(getImageKey(element));
		default: 	return null;
		}
	}

	private String getImageKey(Object element) {
		if (element instanceof Subject) {
			return ConfettiPlugin.IMG_SMALL_SUBJECT;
		} else if (element instanceof Teacher) {
			return ConfettiPlugin.IMG_SMALL_TEACHER;
		} else if (element instanceof StudentGroup) {
			return ConfettiPlugin.IMG_SMALL_STUDENTGROUP;
		} else if (element instanceof Room) {
			return ConfettiPlugin.IMG_SMALL_ROOM;
		} 
		return null;
	}
	 	
	@Override
	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof Nameable) {
			return getTextByColumn(columnIndex, ((Nameable) element).getName().getValue());
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
