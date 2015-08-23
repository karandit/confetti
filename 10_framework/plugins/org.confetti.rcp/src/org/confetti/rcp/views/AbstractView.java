package org.confetti.rcp.views;

import org.confetti.core.DataProvider;
import org.confetti.observable.ObservableListener;
import org.confetti.observable.ObservableValue;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.part.ViewPart;

public abstract class AbstractView<T extends StructuredViewer> extends ViewPart{

	private T viewer;
	
	protected abstract T createViewer(Composite parent);
	protected abstract Object getInput(DataProvider dp);

	@Override
	public void createPartControl(Composite parent) {
		viewer = createViewer(parent);
		viewer.setContentProvider(getContentProvider());
		viewer.setLabelProvider(getLabelProvider());
		ObservableValue<DataProvider> dpObs = ConfettiPlugin.getDefault().getDataProvider();
		dpObs.attachListener(new ObservableListener<DataProvider>() {
			
			@Override
			public void valueChanged(Object src, DataProvider oldDp, DataProvider newDp) {
				inputChanged(oldDp, newDp);
				viewer.setInput(getNullSafeInput(newDp));
			}
		});
		viewer.setInput(getNullSafeInput(dpObs.getValue()));
		inputChanged(null, dpObs.getValue());
		
		//create context menu
		MenuManager menuManager = new MenuManager();
		Menu menu = menuManager.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuManager, viewer);
		getSite().setSelectionProvider(viewer);
	}

	private Object getNullSafeInput(DataProvider newDp) {
		return newDp == null ? null : getInput(newDp);
	}

	protected void inputChanged(DataProvider oldDp, DataProvider newDp) {
	}
	
	protected LabelProvider getLabelProvider() { return new EntityTableLabelProvider(); }
	protected IContentProvider getContentProvider() { return new ArrayContentProvider(); }
	@Override public void setFocus() { viewer.getControl().setFocus(); }
	
}
