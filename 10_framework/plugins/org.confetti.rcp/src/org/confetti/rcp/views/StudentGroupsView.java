package org.confetti.rcp.views;

import static com.google.common.collect.Iterables.isEmpty;
import static com.google.common.collect.Iterables.toArray;

import java.util.List;

import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;
import org.confetti.core.StudentGroup;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableListener;
import org.confetti.rcp.nls.Messages;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeColumn;

public class StudentGroupsView extends AbstractView<TreeViewer> {

	public static final String ID = "org.confetti.rcp.studentGroupsView"; //$NON-NLS-1$
    
	private ObservableListener<String> nameListener;
	private ObservableListener<Assignment> assgCountListener;
	private ObservableListener<StudentGroup> listListener;

	@Override
	protected TreeViewer createViewer(Composite parent) {
		TreeViewer treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		treeViewer.getTree().setHeaderVisible(true);
		createColumn(treeViewer, Messages.General_Name, 170);
		createColumn(treeViewer, "#", 50); //$NON-NLS-1$
		
		nameListener = (Object src, String oldValue, String newValue) -> {
                treeViewer.refresh(src, true);
        };
        assgCountListener = (Object src, Assignment oldValue, Assignment newValue) -> {
        		treeViewer.refresh(src, true);
		};
        listListener = (Object src, StudentGroup oldValue, StudentGroup newValue) -> {
        	treeViewer.refresh();
		    if (oldValue != null) {
	            oldValue.getName().detachListener(nameListener);
	            oldValue.getAssignments().detachListener(assgCountListener);
		    }
	        if (newValue != null) {
	            newValue.getName().attachListener(nameListener);
	            newValue.getAssignments().attachListener(assgCountListener);
	        }
        };
		return treeViewer;
	}
	
	@Override protected Object getInput(DataProvider dp) 		{ return dp.getStudentGroups().getList(); }
	@Override protected IContentProvider getContentProvider() 	{ return new StudentGroupContentProvider(); }

	@Override
	protected void dataProviderChanged(DataProvider oldDp, DataProvider newDp) {
	    if (oldDp != null) {
            ObservableList<StudentGroup> obsList = oldDp.getStudentGroups();
            obsList.detachListener(listListener);
            for (StudentGroup studentGroup : obsList.getList()) {
                studentGroup.getName().detachListener(nameListener);
                studentGroup.getAssignments().detachListener(assgCountListener);
                //TODO: the sub studentgroups are not monitored
            }
        }
        if (newDp != null) {
            ObservableList<StudentGroup> obsList = newDp.getStudentGroups();
            obsList.attachListener(listListener);
            for (StudentGroup studentGroup : obsList.getList()) {
                studentGroup.getName().attachListener(nameListener);
                studentGroup.getAssignments().attachListener(assgCountListener);
                //TODO: the sub studentgroups are not monitored
            }
        }
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
		@Override public boolean hasChildren(Object parent) 							{ return !isEmpty(((StudentGroup) parent).getChildren().getList()); }
		@Override public Object[] getChildren(Object parent)							{ return toArray(((StudentGroup) parent).getChildren().getList(), StudentGroup.class); }
	}

}
