package org.confetti.rcp.views;

import java.util.List;

import org.confetti.core.DataProvider;
import org.confetti.core.StudentGroup;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeColumn;

import com.google.common.collect.Iterables;

public class StudentGroupsView extends AbstractEntityView<TreeViewer> {

	public static final String ID = "org.confetti.rcp.studentGroupsView";

	@Override protected IContentProvider getContentProvider() 	{ return new StudentGroupContentProvider(); }
	@Override protected Object getInput(DataProvider dp) 		{ return dp.getStudentGroups().getList(); }

	@Override
	protected TreeViewer createViewer(Composite parent) {
		TreeViewer viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		viewer.getTree().setHeaderVisible(true);
		createColumn(viewer, "Name", 170);
		createColumn(viewer, "#", 50);

		return viewer;
	}

	static void createColumn(TreeViewer viewer, String title, int width) {
		TreeColumn name = new TreeViewerColumn(viewer, SWT.LEFT).getColumn();
		name.setText(title);
		name.setWidth(width);
	}
	
	class StudentGroupContentProvider implements IStructuredContentProvider, ITreeContentProvider {
		
		@Override public void inputChanged(Viewer v, Object oldInput, Object newInput) 	{ }
		@Override public void dispose() 												{ }
		@Override public Object[] getElements(Object parent) 							{ return ((List<?>) parent).toArray(); }
		@Override public Object getParent(Object child) 								{ return ((StudentGroup) child).getParent(); }
		@Override public boolean hasChildren(Object parent) 							{ return ((StudentGroup) parent).getChildren().getList().iterator().hasNext(); }
		@Override public Object[] getChildren(Object parent)							{ return Iterables.toArray(((StudentGroup) parent).getChildren().getList(), StudentGroup.class); }
	}

}
