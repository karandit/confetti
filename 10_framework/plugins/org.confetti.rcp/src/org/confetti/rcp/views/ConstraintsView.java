package org.confetti.rcp.views;

import static org.confetti.rcp.views.AbstractEntityTableView.createColumn;

import org.confetti.core.Constraint;
import org.confetti.core.ConstraintAttributes;
import org.confetti.core.Constraintable;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableListener;
import org.confetti.rcp.extensions.ConstraintDescr;
import org.confetti.rcp.extensions.ConstraintRegistry;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import com.google.common.collect.Lists;

public class ConstraintsView extends ViewPart {
	
	public static final String ID = "org.confetti.rcp.constraintsView";
	
	private ISelectionListener selectionListener;
	private ObservableList<Constraint> actualList;
	
	@Override
	public void createPartControl(Composite parent) {
		final TableViewer viewer = createViewer(parent);
		
		/* Some attribute's values of a constraint changed. */
		ObservableListener<ConstraintAttributes> attrListener = 
				(Object src, ConstraintAttributes oldValue, ConstraintAttributes newValue) -> {
					viewer.refresh(src, true);
		};
		
		/* An existing constraint removed or a new constraint added. */
		ObservableListener<Constraint> listListener = (Object src, Constraint oldValue, Constraint newValue) -> {
				viewer.refresh();
				if (oldValue != null) { oldValue.getAttributes().detachListener(attrListener); }
				if (newValue != null) { newValue.getAttributes().attachListener(attrListener); }
		};

		selectionListener = (IWorkbenchPart part, ISelection selection) -> {
			    if (ConstraintsView.ID.equals(part.getSite().getId())) {
				    return; //do nothing when the selection comes from this view
				}
				if (actualList != null) {
					actualList.detachListener(listListener);
					actualList.getList().forEach(constr -> constr.getAttributes().detachListener(attrListener));
				}
				actualList = getInput(selection);
				if (actualList != null) {
					actualList.attachListener(listListener);
					actualList.getList().forEach(constr -> constr.getAttributes().attachListener(attrListener));
				}
				viewer.setInput(actualList == null ? null : actualList.getList());
		};
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);
	
		AbstractView.createContextMenu(viewer, getSite());
	}

	@Override
	public void dispose() {
	    getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(selectionListener);
	    super.dispose();
	}

	@Override public void setFocus() { } 
	
	//----------------- helpers ----------------------------------------------------------------------------------------
	private static TableViewer createViewer(Composite parent) {
		Table table = new Table(parent, SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		table.setHeaderVisible(true);
		createColumn(table, "Type", 170);
		createColumn(table, "Details", 200);
		
		TableViewer viewer = new TableViewer(table);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new ConstraintLabelProvider());
		return viewer;
	}
	
	private static ObservableList<Constraint> getInput(ISelection selection) {
		//empty the view when selection is empty
		if (selection.isEmpty()) {
		    return null;
		}
		//if the selection is Entity/Assignment/Root show it's Constraints 
		Object first = ((IStructuredSelection) selection).getFirstElement();
		return (first instanceof Constraintable) ? ((Constraintable) first).getConstraints() : null;
	}
	
	private static class ConstraintLabelProvider extends LabelProvider implements ITableLabelProvider {

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
					ConstraintAttributes attrs = constraint.getAttributes().getValue();
					return 	String.join("; ", 
							Lists.transform(constraintDescr.getFields(), field -> 
							String.format("%s = %s", field.getLabel(), field.printValue(attrs, constraint))));
				default: return "";
			}
		}
	}

}
