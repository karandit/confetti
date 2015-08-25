package org.confetti.rcp.views;

import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;
import org.confetti.core.Entity;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Gabor Bubla
 *
 */
public abstract class AbstractEntityTableView<T extends Entity> extends AbstractView<TableViewer> {

	private TableViewer tableViewer;
	private ObservableListener<String> nameListener;
	private ObservableListener<Assignment> assgCountListener;
	private ObservableListener<T> listListener;
	
	@Override
	protected TableViewer createViewer(Composite parent) {
		Table table = new Table(parent, SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		table.setHeaderVisible(true);
		createColumn(table, "Name", 170);
		createColumn(table, "#", 50);
		
		tableViewer = new TableViewer(table);
		nameListener = (Object src, String oldValue, String newValue) -> {
				tableViewer.refresh(src, true);
		};
        assgCountListener = (Object src, Assignment oldValue, Assignment newValue) -> {
    		tableViewer.refresh(src, true);
        };
		listListener = (Object src, T oldValue, T newValue) -> {
				tableViewer.refresh();
				if (oldValue != null) {
					oldValue.getName().detachListener(nameListener);
		            oldValue.getAssignments().detachListener(assgCountListener);
				}
				if (newValue != null) {
					newValue.getName().attachListener(nameListener);
		            newValue.getAssignments().attachListener(assgCountListener);
				}
		};
		return tableViewer;
	}
	
	@Override protected Object getInput(DataProvider dp) { return getObservableList(dp).getList(); }

	protected abstract ObservableList<T> getObservableList(DataProvider dp);
	
	@Override
	protected void dataProviderChanged(DataProvider oldDp, DataProvider newDp) {
		if (oldDp != null) {
			ObservableList<T> obsList = getObservableList(oldDp);
			obsList.detachListener(listListener);
			for (Entity entity : obsList.getList()) {
				entity.getName().detachListener(nameListener);
				entity.getAssignments().detachListener(assgCountListener);
			}
		}
		if (newDp != null) {
			ObservableList<T> obsList = getObservableList(newDp);
			obsList.attachListener(listListener);
			for (Entity entity : obsList.getList()) {
				entity.getName().attachListener(nameListener);
				entity.getAssignments().attachListener(assgCountListener);
			}
		}
	}

	public static void createColumn(Table table, String title, int width) {
		TableColumn tc = new TableColumn(table, SWT.LEFT);
		tc.setText(title);
		tc.setWidth(width);
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}
	
}
