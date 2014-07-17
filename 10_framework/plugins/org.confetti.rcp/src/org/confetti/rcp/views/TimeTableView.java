package org.confetti.rcp.views;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

public class TimeTableView extends ViewPart {

	public static final String ID = "org.confetti.rcp.timeTableView";
	
	private TableViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		Table table = new Table(parent, SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		table.setHeaderVisible(true);
		
		TableColumn time = new TableColumn(table, SWT.LEFT);
		time.setText("Time");
		TableColumn monday = new TableColumn(table, SWT.LEFT);
		monday.setText("Monday");
		TableColumn tuesday = new TableColumn(table, SWT.LEFT);
		tuesday.setText("Tuesday");
		TableColumn wednesday = new TableColumn(table, SWT.LEFT);
		wednesday.setText("Wednesday");
		TableColumn thursday = new TableColumn(table, SWT.LEFT);
		thursday.setText("Thursday");
		TableColumn friday = new TableColumn(table, SWT.LEFT);
		friday.setText("Friday");
		
		viewer = new TableViewer(table);
//		viewer.setContentProvider(new ArrayContentProvider());
//		viewer.setLabelProvider(new TeacherLabelProvider());
//		viewer.setInput(Part3Plugin.getDefault().getDummyModel().getTeachers());
		
		for (TableColumn tc : table.getColumns()) {
			tc.pack();
		}
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

}
