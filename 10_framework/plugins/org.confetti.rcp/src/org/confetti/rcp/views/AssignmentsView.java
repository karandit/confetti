package org.confetti.rcp.views;

import java.util.ArrayList;
import java.util.List;

import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;
import org.confetti.core.Entity;
import org.confetti.core.Nameable;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableListener;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import de.kupzog.ktable.KTable;

public class AssignmentsView extends ViewPart {

	public final static String ID = "org.confetti.rcp.assignmentsView";
	
	private TableViewer tableViewer;
	private ObservableListener<String> nameListener;
	
	@Override
	public void createPartControl(Composite parent) {
		SashForm form = new SashForm(parent, SWT.VERTICAL);
		form.setLayout(new FillLayout());
		createAssigmentsList(form);
		createTimeTable(form);
	}

	private void createAssigmentsList(Composite parent) {
		Table table = new Table(parent, SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		table.setHeaderVisible(true);
		AbstractEntityTableView.createColumn(table, "#", 50);
		AbstractEntityTableView.createColumn(table, "Subject", 150);
		AbstractEntityTableView.createColumn(table, "Teacher", 150);
		AbstractEntityTableView.createColumn(table, "Student Group", 150);
		AbstractEntityTableView.createColumn(table, "Room", 150);
		
		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new AssignmentLabelProvider());
		
		nameListener = new ObservableListener<String>() {
			@Override
			public void valueChanged(Object src, String oldValue, String newValue) {
				tableViewer.refresh();
			}
		};
		
		ISelectionService selectionService = this.getSite().getWorkbenchWindow().getSelectionService();
		selectionService.addSelectionListener(new ISelectionListener() {
			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				if (!selection.isEmpty()) { 
					IStructuredSelection strSel = (IStructuredSelection) selection;
					Object first = strSel.getFirstElement();
					if (first instanceof Entity) {
						Entity source = (Entity) first;
						//TODO detach the listener somewhere? :/
						source.getName().attachListener(nameListener);
						tableViewer.setInput(source.getAssignments().getList());
						return;
					}
				}
				tableViewer.setInput(null);
			}
		});
	}

	private TimeTableModel createTimeTable(Composite parent) {
		final KTable ktable = new KTable(parent, SWT.NONE);
		ktable.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));

		final TimeTableModel model = new TimeTableModel(ktable);
		ktable.setModel(model);
		model.initialize();
		
		ConfettiPlugin.getDefault().getDataProvider().attachListener(new ObservableListener<DataProvider>() {
			@Override
			public void valueChanged(Object src, DataProvider oldValue, DataProvider newValue) {
				if (newValue != null) {
					TimeTableModel newModel = new TimeTableModel(ktable, 
							getNames(newValue.getDays()), getNames(newValue.getHours()));
					ktable.setModel(newModel);
					newModel.initialize();
				}
			}
		});
		return model;
	}

	private String[] getNames(ObservableList<? extends Nameable> items) {
		List<String> names = new ArrayList<>();
		for (Nameable nameable : items.getList()) {
			names.add(nameable.getName().getValue());
		}
		return names.toArray(new String[names.size()]);
	}
	
	@Override
	public void setFocus() {
	}

	private static class AssignmentLabelProvider extends LabelProvider implements ITableLabelProvider {

		@Override public Image getColumnImage(Object element, int columnIndex) { return null; }

		@Override
		public String getColumnText(Object element, int columnIndex) {
			Assignment assignment = (Assignment) element;
			switch (columnIndex) {
				case 0: return "1/4";
				case 1:	return getName(assignment.getSubject());
				case 2:	return toStr(assignment.getTeachers().getList());
				case 3:	return toStr(assignment.getStudentGroups().getList());
				default : return getName(assignment.getRoom());
			}
		}

		private static String getName(Entity ent) { return ent == null ? null : ent.getName().getValue(); }

		private static <T extends Entity> String toStr(Iterable<T> items) {
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
	
}
