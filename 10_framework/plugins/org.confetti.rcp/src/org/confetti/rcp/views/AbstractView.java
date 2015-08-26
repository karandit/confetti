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
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.part.ViewPart;

public abstract class AbstractView<T extends StructuredViewer> extends ViewPart{

	private T viewer;
	private ObservableListener<DataProvider> dpListener;
	
	protected abstract T createViewer(Composite parent);
	protected abstract Object getInput(DataProvider dp);
	protected abstract void dataProviderChanged(DataProvider oldDp, DataProvider newDp);

	@Override
	public void createPartControl(Composite parent) {
		viewer = createViewer(parent);
		viewer.setContentProvider(getContentProvider());
		viewer.setLabelProvider(getLabelProvider());
		ObservableValue<DataProvider> dpObs = ConfettiPlugin.getDefault().getDataProvider();
		dpListener = (Object src, DataProvider oldDp, DataProvider newDp) -> {
				dataProviderChanged(oldDp, newDp);
				viewer.setInput(getNullSafeInput(newDp));
		};
		dpObs.attachListener(dpListener);
		viewer.setInput(getNullSafeInput(dpObs.getValue()));
		dataProviderChanged(null, dpObs.getValue());
		
		createContextMenu(viewer, getSite());
	}
	
	private Object getNullSafeInput(DataProvider newDp) {
		return newDp == null ? null : getInput(newDp);
	}
	
	protected LabelProvider getLabelProvider() { return new EntityTableLabelProvider(); }
	protected IContentProvider getContentProvider() { return new ArrayContentProvider(); }
	@Override public void setFocus() { viewer.getControl().setFocus(); }

	@Override
	public void dispose() {
		ObservableValue<DataProvider> dpObs = ConfettiPlugin.getDefault().getDataProvider();
		dpObs.detachListener(dpListener);
		dataProviderChanged(dpObs.getValue(), null);
		super.dispose();
	}
	
	static void createContextMenu(StructuredViewer viewer, IWorkbenchPartSite site) {
		MenuManager menuManager = new MenuManager();
		Menu menu = menuManager.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		site.registerContextMenu(menuManager, viewer);
		site.setSelectionProvider(viewer);
	}

}
