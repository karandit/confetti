package org.confetti.rcp.views;

import static org.confetti.rcp.views.StudentGroupsView.createColumn;

import java.util.List;

import org.confetti.core.DataProvider;
import org.confetti.core.Nameable;
import org.confetti.core.StudentGroup;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.common.collect.Iterables;

/**
 * @author Bubla Gabor
 */
public class InstituteView extends AbstractEntityView<TreeViewer> {

	public static final String ID = "org.confetti.rcp.instituteView";

	@Override
	protected TreeViewer createViewer(Composite parent) {
		TreeViewer viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		viewer.getTree().setHeaderVisible(true);
		createColumn(viewer, "Name", 170);
		createColumn(viewer, "#", 50);

		return viewer;
	}
	
	@Override protected Object getInput(DataProvider dp) { return dp; }
	@Override protected IContentProvider getContentProvider() { return new AllEntitiesContentProvider(); }

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
			return Iterables.toArray(getChildren(dp), Object.class);
		}
		
		public boolean hasChildren(DataProvider dp) { return !Iterables.isEmpty(getChildren(dp)); }
		protected abstract Iterable<?> getChildren(DataProvider dp);
	}

	class AllEntitiesContentProvider implements IStructuredContentProvider, ITreeContentProvider 
	{
		
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
				return Containers.values();
			}
			if (parent instanceof Containers) {
				return ((Containers) parent).getChildrenAsArray(dp);
			}
			if (parent instanceof StudentGroup) {
				List<StudentGroup> children = ((StudentGroup) parent).getChildren();
				return children.toArray(new Object[children.size()]);
			}
			
			return new Object[0];
		}

		@Override
		public boolean hasChildren(Object parent) {
			if (parent instanceof DataProvider) {
				return true;
			}
			if (parent instanceof Containers) {
				return ((Containers) parent).hasChildren(dp);
			}
			if (parent instanceof StudentGroup) {
				return !((StudentGroup) parent).getChildren().isEmpty();
			}
			return false;
		}
	}
}
