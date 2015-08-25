package org.confetti.rcp.views;

import static com.google.common.collect.Iterables.isEmpty;
import static com.google.common.collect.Iterables.toArray;
import static org.confetti.rcp.views.StudentGroupsView.createColumn;

import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;
import org.confetti.core.Nameable;
import org.confetti.core.StudentGroup;
import org.confetti.observable.ObservableListener;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Bubla Gabor
 */
public class InstituteView extends AbstractView<TreeViewer> {

	public static final String ID = "org.confetti.rcp.instituteView";

	private ObservableListener<String> instNameListener;
	private ObservableListener<Assignment> assgCountListener;

	@Override
	protected TreeViewer createViewer(Composite parent) {
		TreeViewer viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		viewer.getTree().setHeaderVisible(true);
		createColumn(viewer, "Name", 170);
		createColumn(viewer, "#", 50);
		instNameListener = (Object src, String oldValue, String newValue) -> {
			viewer.refresh(Root.All, true);
		};
        assgCountListener = (Object src, Assignment oldValue, Assignment newValue) -> {
    		viewer.refresh(Root.All, true);
	};
		return viewer;
	}
	
	@Override protected Object getInput(DataProvider dp) { return dp; }
	@Override protected IContentProvider getContentProvider() { return new AllEntitiesContentProvider(); }
	@Override
	protected void dataProviderChanged(DataProvider oldDp, DataProvider newDp) {
		if (oldDp != null) {
			oldDp.getName().detachListener(instNameListener);
			oldDp.getAssignments().detachListener(assgCountListener);
		}
		if (newDp != null) {
			newDp.getName().attachListener(instNameListener);
			newDp.getAssignments().attachListener(assgCountListener);
		}
	}
	
	//----------------------------- helper classes ---------------------------------------------------------------------
	enum Containers implements Nameable {
		AllSubjects("All subjects") {
			@Override public Iterable<?> getChildren(DataProvider dp) { return dp.getSubjects().getList(); }
		},
		AllTeachers("All teachers") {
			@Override public Iterable<?> getChildren(DataProvider dp) { return dp.getTeachers().getList(); }
		},
		AllStudentGroups("All student groups") {
			@Override public Iterable<?> getChildren(DataProvider dp) { return dp.getStudentGroups().getList(); }
		},
		AllRooms("All rooms") {
			@Override public Iterable<?> getChildren(DataProvider dp) { return dp.getRooms().getList(); }
		};
		
		private final ValueMutator<String> nameMut;
		private Containers(final String name) {
			nameMut = new ValueMutator<>(this, name);
		}
		@Override public ObservableValue<String> getName() { return nameMut.getObservableValue(); }
		
		public Object[] getChildrenAsArray(DataProvider dp) {
			return toArray(getChildren(dp), Object.class);
		}
		
		public boolean hasChildren(DataProvider dp) { return !isEmpty(getChildren(dp)); }
		protected abstract Iterable<?> getChildren(DataProvider dp);
	}

	class AllEntitiesContentProvider implements IStructuredContentProvider, ITreeContentProvider {
		
		private DataProvider dp;

		@Override
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			dp = (DataProvider) newInput;
		}

		@Override
		public void dispose() {
		}

		@Override
		public Object[] getElements(Object parent) {
			return getChildren(parent);
		}

		@Override
		public Object getParent(Object child) {
			if (child instanceof DataProvider) {
				return null;
			}
			return null;
		}

		@Override
		public Object[] getChildren(Object parent) {
			if (parent instanceof DataProvider) {
				return Root.values();
			}
			if (parent instanceof Root) {
				return Containers.values();
			}
			if (parent instanceof Containers) {
				return ((Containers) parent).getChildrenAsArray(dp);
			}
			if (parent instanceof StudentGroup) {
				return toArray(((StudentGroup) parent).getChildren().getList(), StudentGroup.class);
			}
			
			return new Object[0];
		}

		@Override
		public boolean hasChildren(Object parent) {
			if (parent instanceof DataProvider) {
				return true;
			}
			if (parent instanceof Root) {
				return true;
			}
			if (parent instanceof Containers) {
				return ((Containers) parent).hasChildren(dp);
			}
			if (parent instanceof StudentGroup) {
				return !isEmpty(((StudentGroup) parent).getChildren().getList());
			}
			return false;
		}
	}
	
}
