package org.confetti.rcp.views;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class ConstraintsView extends ViewPart {

	public static final String ID = "org.confetti.rcp.constraintsView";
	
	private ListViewer viewer;
	
	@Override
	public void createPartControl(Composite parent) {
		viewer = new ListViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

}
