package org.confetti.rcp;


import org.confetti.core.Entity;
import org.eclipse.jface.viewers.LabelProvider;

public class EntityLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		return ((Entity) element).getName();
	}
}
