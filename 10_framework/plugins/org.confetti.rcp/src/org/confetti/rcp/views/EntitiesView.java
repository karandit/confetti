package org.confetti.rcp.views;

import org.confetti.core.DataProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeColumn;

/**
 * @author Bubla Gabor
 */
public class EntitiesView extends AbstractEntityView<TreeViewer> {

	public static final String ID = "org.confetti.rcp.entitiesView";

	@Override
	protected TreeViewer createViewer(Composite parent) {
		TreeViewer viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		viewer.getTree().setHeaderVisible(true);
		createColumn(viewer, "Name", 170);
		createColumn(viewer, "#", 50);

		return viewer;
	}
	
	private void createColumn(TreeViewer viewer, String title, int width) {
		TreeColumn name = new TreeViewerColumn(viewer, SWT.LEFT).getColumn();
		name.setText(title);
		name.setWidth(width);
	}

	@Override
	protected Object getInput(DataProvider dp) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
