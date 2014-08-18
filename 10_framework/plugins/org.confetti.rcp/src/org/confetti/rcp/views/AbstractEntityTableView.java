package org.confetti.rcp.views;

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
public abstract class AbstractEntityTableView<T extends Entity> extends AbstractEntityView<TableViewer> implements ObservableListener<T> {

	private TableViewer tableViewer;
	protected TableViewer createViewer(Composite parent) {
		Table table = new Table(parent, SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		table.setHeaderVisible(true);
//		table.setLinesVisible(true);
		createColumn(table, "Name", 170);
		createColumn(table, "#", 50);
		
		tableViewer = new TableViewer(table);
		return tableViewer;
	}
	
	@Override protected Object getInput(DataProvider dp) { return getObservableList(dp).getList(); }

	protected abstract ObservableList<T> getObservableList(DataProvider dp);
	
	@Override
	protected void inputChanged(DataProvider oldDp, DataProvider newDp) {
		if (oldDp != null) {
			ObservableList<T> obsList = getObservableList(oldDp);
			obsList.detachListener(this);
		}
		if (newDp != null) {
			ObservableList<T> obsList = getObservableList(newDp);
			obsList.attachListener(this);
		}
	}

	@Override
	public void valueChanged(T oldValue, T newValue) {
		tableViewer.refresh();
	}
	
	private void createColumn(Table table, String title, int width) {
		TableColumn tc = new TableColumn(table, SWT.LEFT);
		tc.setText(title);
		tc.setWidth(width);
	}
	
}
