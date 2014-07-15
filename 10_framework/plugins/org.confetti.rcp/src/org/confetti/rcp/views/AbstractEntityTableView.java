/**
 * 
 */
package org.confetti.rcp.views;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Gabor Bubla
 *
 */
public abstract class AbstractEntityTableView extends AbstractEntityView<TableViewer> {

	protected TableViewer createViewer(Composite parent) {
		Table table = new Table(parent, SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		table.setHeaderVisible(true);
//		table.setLinesVisible(true);
		createColumn(table, "Name", 170);
		createColumn(table, "#", 50);
		
		return new TableViewer(table);
	}
	
	private void createColumn(Table table, String title, int width) {
		TableColumn tc = new TableColumn(table, SWT.LEFT);
		tc.setText(title);
		tc.setWidth(width);
	}
	
}
