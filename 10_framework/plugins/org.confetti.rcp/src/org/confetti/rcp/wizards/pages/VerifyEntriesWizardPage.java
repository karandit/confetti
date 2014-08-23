package org.confetti.rcp.wizards.pages;

import org.confetti.rcp.wizards.models.Problem;
import org.confetti.rcp.wizards.models.VerifyEntriesModel;
import org.confetti.util.Tuple;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class VerifyEntriesWizardPage extends ModelableWizardPage<VerifyEntriesModel> implements IWizardPageNavigatable {

	private TableViewer tableViewer;
	private Table table;

	public VerifyEntriesWizardPage(VerifyEntriesModel model) {
		super("Verify", model.getVerifyEntriesPageTitle(), null, model);
		setDescription(getModel().getVerifyEntriesPageDescription());
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		table = new Table(parent, SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.MULTI);
		
		TableColumn names = new TableColumn(table, SWT.NONE);
		names.setText("Name");
		names.setWidth(150);
		TableColumn check = new TableColumn(table, SWT.NONE);
		check.setText("Status");
		check.setWidth(250);
		table.setHeaderVisible(true);
		
		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new ITableLabelProvider() {
			
			@Override
			public String getColumnText(Object element, int columnIndex) {
				Tuple<String, Problem> nameAndProblem = (Tuple<String, Problem>) element;
				switch (columnIndex) {
				case 0: return nameAndProblem.getFirst();
				case 1: return nameAndProblem.getSecond().getDescription();
				default: return "";
				}
			}
			
			@Override public Image getColumnImage(Object element, int columnIndex) { return null; }
			@Override public void addListener(ILabelProviderListener listener) { }
			@Override public void dispose() { }
			@Override public boolean isLabelProperty(Object element, String property) { return false; }
			@Override public void removeListener(ILabelProviderListener listener) { }
		});
		setControl(tableViewer.getControl());
	}

	@Override
	public void pageShowed() {
		tableViewer.setInput(getModel().getResult());
		boolean ok = true;
		for (Tuple<String, Problem> item : getModel().getResult()) {
			if (item.getSecond() != Problem.OK) {
				ok = false;
				break;
			}
		}
		setPageComplete(ok);
	}

	@Override
	public void pageHid() {
//		setPageComplete(false);
	}
}
