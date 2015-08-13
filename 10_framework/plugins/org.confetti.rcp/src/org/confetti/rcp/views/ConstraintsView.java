package org.confetti.rcp.views;

import org.confetti.core.Constraint;
import org.confetti.core.ConstraintAttribute;
import org.confetti.core.DataProvider;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableListener;
import org.confetti.observable.ObservableValue;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.extensions.ConstraintDescr;
import org.confetti.rcp.extensions.ConstraintRegistry;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

public class ConstraintsView extends ViewPart implements ObservableListener<Constraint> {

	public static final String ID = "org.confetti.rcp.constraintsView";
	
	private TableViewer viewer;
	
	@Override
	public void createPartControl(Composite parent) {
		Table table = new Table(parent, SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		table.setHeaderVisible(true);
		createColumn(table, "Type", 170);
		createColumn(table, "Details", 200);
		viewer = new TableViewer(table);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new ConstraintLabelProvider());
        
		ObservableValue<DataProvider> dpObs = ConfettiPlugin.getDefault().getDataProvider();
		dpObs.attachListener(new ObservableListener<DataProvider>() {
			
			@Override
			public void valueChanged(Object src, DataProvider oldDp, DataProvider newDp) {
				inputChanged(oldDp, newDp);
				viewer.setInput(getNullSafeInput(newDp));
			}
		});
		viewer.setInput(getNullSafeInput(dpObs.getValue()));
		inputChanged(null, dpObs.getValue());
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	@Override
	public void valueChanged(Object src, Constraint oldValue, Constraint newValue) {
		viewer.refresh();
	}
	
	private Object getNullSafeInput(DataProvider newDp) {
		return newDp == null ? null : getInput(newDp);
	}

	private Object getInput(DataProvider dp) {
		return getObservableList(dp).getList();
	}
	
	private ObservableList<Constraint> getObservableList(DataProvider dp) {
		return dp.getConstraints();
	}

	private void inputChanged(DataProvider oldDp, DataProvider newDp) {
		if (oldDp != null) {
			ObservableList<Constraint> obsList = getObservableList(oldDp);
			obsList.detachListener(this);
		}
		if (newDp != null) {
			ObservableList<Constraint> obsList = getObservableList(newDp);
			obsList.attachListener(this);
		}
	}
	
	private static void createColumn(Table table, String title, int width) {
		TableColumn tc = new TableColumn(table, SWT.LEFT);
		tc.setText(title);
		tc.setWidth(width);
	}
	public static class ConstraintLabelProvider extends LabelProvider implements ITableLabelProvider {

		@Override public Image getColumnImage(Object element, int columnIndex) { return null; }

		@Override
		public String getColumnText(Object element, int columnIndex) {
			Constraint constraint = (Constraint) element;
			switch (columnIndex) {
			case 0: 
				String constraintType = constraint.getConstraintType();
				ConstraintRegistry reg = ConstraintRegistry.INSTANCE;
				ConstraintDescr constraintDescr = reg.getConstraintDescrById(constraintType);
				return constraintDescr == null ? "" : constraintDescr.getName();
			case 1: 
				StringBuilder sb = new StringBuilder();
				for (ConstraintAttribute attr : constraint.getAttributes()) {
					sb.append(attr.getKey()).append("; ");
				}
				return sb.toString();
			default: return "";
			}
		}
	}


}
