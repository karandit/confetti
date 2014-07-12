package org.confetti.rcp.views;

import org.confetti.rcp.Part3Plugin;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

public class SubjectsView extends ViewPart {

	public static final String ID = "org.confetti.rcp.subjectsView";
	
	private TableViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		Table table = new Table(parent, SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		table.setHeaderVisible(true);
//		table.setLinesVisible(true);
		TableColumn coverage = new TableColumn(table, SWT.LEFT);
		coverage.setText("#");
		TableColumn name = new TableColumn(table, SWT.LEFT);
		name.setText("Name");
		
		viewer = new TableViewer(table);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new EntityTableLabelProvider());
//		viewer.setInput(Part3Plugin.getDefault().getDummyModel().getSubjects());

		getSite().setSelectionProvider(viewer);
		
		for (TableColumn tc : table.getColumns()) {
			tc.pack();
		}
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

}
