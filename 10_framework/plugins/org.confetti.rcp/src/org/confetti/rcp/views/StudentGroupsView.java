package org.confetti.rcp.views;

import java.util.List;

import org.confetti.core.StudentGroup;
import org.confetti.rcp.Part3Plugin;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class StudentGroupsView extends ViewPart {

	public static final String ID = "org.confetti.rcp.studentGroupsView";

	private TreeViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		viewer.getTree().setHeaderVisible(true);
		TreeViewerColumn coverage = new TreeViewerColumn(viewer, SWT.LEFT);
		coverage.getColumn().setText("#");

		TreeViewerColumn name = new TreeViewerColumn(viewer, SWT.LEFT);
		name.getColumn().setText("Name");

		viewer.setContentProvider(new StudentGroupContentProvider());
		viewer.setLabelProvider(new EntityTableLabelProvider());
		viewer.setInput(Part3Plugin.getDefault().getDummyModel().getStudentGroups());
		 
		getSite().setSelectionProvider(viewer);

		coverage.getColumn().pack();	
		name.getColumn().pack();	
	
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	class StudentGroupContentProvider implements IStructuredContentProvider, ITreeContentProvider {

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			return ((List<?>) parent).toArray();
		}

		public Object getParent(Object child) {
			return ((StudentGroup) child).getParent();
		}

		public Object[] getChildren(Object parent) {
			return ((StudentGroup) parent).getChildren().toArray();
		}

		public boolean hasChildren(Object parent) {
			return !((StudentGroup) parent).getChildren().isEmpty();
		}
	}

}
