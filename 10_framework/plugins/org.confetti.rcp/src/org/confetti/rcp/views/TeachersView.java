package org.confetti.rcp.views;


import org.confetti.core.Teacher;
import org.confetti.rcp.Part3Plugin;
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

public class TeachersView extends ViewPart {

	public static final String ID = "org.confetti.rcp.teachersView";
	
	private TableViewer viewer;
	
	@Override
	public void createPartControl(Composite parent) {
		Table table = new Table(parent, SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		table.setHeaderVisible(true);
//		table.setLinesVisible(true);
		TableColumn coverage = new TableColumn(table, SWT.LEFT);
		coverage.setText("#");
		TableColumn tcName = new TableColumn(table, SWT.LEFT);
		tcName.setText("Name");
		
		viewer = new TableViewer(table);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new TeacherLabelProvider());
		viewer.setInput(Part3Plugin.getDefault().getDummyModel().getTeachers());

		getSite().setSelectionProvider(viewer);
		
		for (TableColumn tc : table.getColumns()) {
			tc.pack();
		}
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	private static class TeacherLabelProvider extends LabelProvider implements ITableLabelProvider {

		@Override public Image getColumnImage(Object element, int columnIndex) { return null; }

		@Override
		public String getColumnText(Object element, int columnIndex) {
			Teacher teacher = (Teacher) element;
			switch (columnIndex) {
				case 0: return "1/4";
				default: return teacher.getName();
			}
		}
		
	}
}
