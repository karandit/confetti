package org.confetti.rcp.views;

import org.confetti.rcp.EntityLabelProvider;
import org.confetti.rcp.Part3Plugin;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class RoomsView extends ViewPart {

	public static final String ID = "org.confetti.rcp.roomsView";
	
	private ListViewer viewer;
	
	@Override
	public void createPartControl(Composite parent) {
		viewer = new ListViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new EntityLabelProvider());
		viewer.setInput(Part3Plugin.getDefault().getDummyModel().getRooms());
		
		getSite().setSelectionProvider(viewer);
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

}
