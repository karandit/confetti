package org.confetti.rcp.views;

import java.util.List;

import org.confetti.core.Constraint;
import org.confetti.core.ConstraintAttributes;
import org.confetti.core.DataProvider;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableListener;
import org.confetti.rcp.constraints.ConstraintField;
import org.confetti.rcp.extensions.ConstraintDescr;
import org.confetti.rcp.extensions.ConstraintRegistry;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public class ConstraintsView extends AbstractEntityView<TableViewer> implements ObservableListener<Constraint> {

	public static final String ID = "org.confetti.rcp.constraintsView";
	
	private TableViewer viewer;
	
	@Override
	protected TableViewer createViewer(Composite parent) {
		Table table = new Table(parent, SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		table.setHeaderVisible(true);
		AbstractEntityTableView.createColumn(table, "Type", 170);
		AbstractEntityTableView.createColumn(table, "Details", 200);
		viewer = new TableViewer(table);
		return viewer;
	}
	
	@Override
	protected LabelProvider getLabelProvider() {
		return new ConstraintLabelProvider();
	}

	@Override
	public void valueChanged(Object src, Constraint oldValue, Constraint newValue) {
		viewer.refresh();
	}

	@Override
	protected Object getInput(DataProvider dp) {
		return getObservableList(dp).getList();
	}

	private ObservableList<Constraint> getObservableList(DataProvider dp) {
		return dp.getConstraints();
	}
	
	@Override
	protected void inputChanged(DataProvider oldDp, DataProvider newDp) {
		if (oldDp != null) {
			ObservableList<Constraint> obsList = getObservableList(oldDp);
			obsList.detachListener(this);
		}
		if (newDp != null) {
			ObservableList<Constraint> obsList = getObservableList(newDp);
			obsList.attachListener(this);
		}
	}

	public static class ConstraintLabelProvider extends LabelProvider implements ITableLabelProvider {

		@Override public Image getColumnImage(Object element, int columnIndex) { return null; }

		@Override
		public String getColumnText(Object element, int columnIndex) {
			Constraint constraint = (Constraint) element;
			ConstraintRegistry reg = ConstraintRegistry.INSTANCE;
			ConstraintDescr constraintDescr = reg.getConstraintDescrById(constraint.getConstraintType());

			switch (columnIndex) {
			case 0: 
				return constraintDescr == null ? "" : constraintDescr.getName();
			case 1: 
				StringBuilder sb = new StringBuilder();
				ConstraintAttributes attrs = constraint.getAttributes();
				List<ConstraintField> fields = constraintDescr.getFields();
				for (ConstraintField constraintField : fields) {
					sb.append(constraintField.getLabel())
					.append(" = ")
					.append(constraintField.printValue(attrs, constraint))
					.append("; ");
				}
				return sb.toString();
			default: return "";
			}
		}
	}
}
