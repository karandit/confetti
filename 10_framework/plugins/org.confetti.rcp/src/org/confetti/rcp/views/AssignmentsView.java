package org.confetti.rcp.views;

import java.util.List;

import org.confetti.core.Assignable;
import org.confetti.core.Assignment;
import org.confetti.core.Entity;
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
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

public class AssignmentsView extends ViewPart {

	public final static String ID = "org.confetti.rcp.assignmentsView";
	
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
		addColumn(table, "#", 50);
		addColumn(table, "Subject", 150);
		addColumn(table, "Teacher", 150);
		addColumn(table, "Student Group", 150);
		addColumn(table, "Room", 150);
		
		final TableViewer viewer = new TableViewer(table);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new AssignmentLabelProvider());
		
		ISelectionService selectionService = this.getSite().getWorkbenchWindow().getSelectionService();
		selectionService.addSelectionListener(new ISelectionListener() {
			
			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				if (!selection.isEmpty()) { 
					IStructuredSelection strSel = (IStructuredSelection) selection;
					Object first = strSel.getFirstElement();
					if (first instanceof Assignable) {
						Assignable source = (Assignable) first;
						viewer.setInput(source.getAssignments());
					}
				} else {
					viewer.setInput(null);
				}
			}
		});
	}

	private void addColumn(Table table, String name, int width) {
		TableColumn column = new TableColumn(table, SWT.LEFT);
		column.setText(name);
		column.setWidth(width);
	}

	private void createTimeTable(Composite parent) {
//		KTable ktable = new KTable(parent, SWT.NONE);
//		ktable.setModel(new TimeTableModel(ktable));
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
				case 1:	return getName(assignment.getSubj());
				case 2:	return toStr(assignment.getTeachers());
				case 3:	return toStr(assignment.getStudentGroups());
				default : return getName(assignment.getRoom());
			}
		}

		private static String getName(Entity ent) { return ent == null ? null : ent.getName().getValue(); }

		private static <T extends Entity> String toStr(List<T> items) {
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
