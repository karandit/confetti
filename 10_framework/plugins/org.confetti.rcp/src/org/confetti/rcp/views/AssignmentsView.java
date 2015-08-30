package org.confetti.rcp.views;

import org.confetti.core.Assignable;
import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;
import org.confetti.core.Entity;
import org.confetti.core.Nameable;
import org.confetti.core.Subject;
import org.confetti.observable.ObservableListener;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.util.Tuple;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableNoScrollModel;

public class AssignmentsView extends ViewPart {

	public final static String ID = "org.confetti.rcp.assignmentsView";
	
	private ISelectionListener selectionListener;
	
	@Override
	public void createPartControl(Composite parent) {
		SashForm form = new SashForm(parent, SWT.VERTICAL);
		form.setLayout(new FillLayout());
		TableViewer tableViewer = createAssigmentsList(form);
		KTable ktable = createTimeTable(form);
		selectionListener = (IWorkbenchPart part, ISelection selection) -> {
		    if (AssignmentsView.ID.equals(part.getSite().getId())) {
			    return; //do nothing when the selection comes from this view
			}

		    Tuple<Iterable<Assignment>, Entity> input = getInput(selection);
		    tableViewer.setInput(input.getFirst());
		    assignModel(ktable, ConfettiPlugin.getDefault().getDataProvider().getValue(), input.getSecond());
		};

		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);
		AbstractView.createContextMenu(tableViewer, getSite());
	}

	@Override
	public void dispose() {
	    getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(selectionListener);
	    super.dispose();
	}

	@Override public void setFocus() { }

	//---------------------------- helpers -----------------------------------------------------------------------------
	private static TableViewer createAssigmentsList(Composite parent) {
		Table table = new Table(parent, SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		table.setHeaderVisible(true);
		AbstractEntityTableView.createColumn(table, "#", 50);
		AbstractEntityTableView.createColumn(table, "Subject", 150);
		AbstractEntityTableView.createColumn(table, "Teacher", 150);
		AbstractEntityTableView.createColumn(table, "Student Group", 150);
		AbstractEntityTableView.createColumn(table, "Room", 150);
		
		TableViewer tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new AssignmentLabelProvider());
		return tableViewer;
	}
	
	private static KTable createTimeTable(Composite parent) {
		final KTable ktable = new KTable(parent, SWT.NONE);
		ktable.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
		
		assignModel(ktable, null, null);
		
		ConfettiPlugin.getDefault().getDataProvider().attachListener(new ObservableListener<DataProvider>() {
			@Override
			public void valueChanged(Object src, DataProvider oldValue, DataProvider newValue) {
				if (newValue != null) {
					assignModel(ktable, newValue, null);
				}
			}
		});
		return ktable;
	}

	private static Tuple<Iterable<Assignment>, Entity> getInput(ISelection selection) {
		if (selection.isEmpty()) {
			return new Tuple<>(null, null); //empty the view when selection is empty
		}
		
		Object first = ((IStructuredSelection) selection).getFirstElement();
		if (first instanceof Entity) {
			Entity source = (Entity) first;
			return new Tuple<>(source.getAssignments().getList(), (source instanceof Subject) ? null : source);
		} else if (first instanceof Root) {
			return new Tuple<>(((Assignable) first).getAssignments().getList(), null);
		} 
		return new Tuple<>(null, null); // instanceof InstituteView.Containers
	}

	private static void assignModel(final KTable ktable, DataProvider dp, Entity ent) {
		final KTableNoScrollModel model;
		if (dp == null || ent == null) {
			model = new TimeTableNotAvailableModel(ktable);
		} else {
			model = ent.accept(TimeTableModelFactory.INSTANCE, new Tuple<>(dp, ktable));
		}
		ktable.setModel(model);
		model.initialize();
	}
	
	public static String getName(Nameable ent) { return ent == null ? null : ent.getName().getValue(); }
	
	public static <T extends Entity> String toStr(Iterable<T> items) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (T t : items) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			sb.append(getName(t));
		}
		return sb.toString();
	}
	
}
