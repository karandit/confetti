package org.confetti.rcp.views;

import org.confetti.core.DataProvider;
import org.confetti.observable.ObservableListener;
import org.confetti.observable.ObservableValue;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public abstract class AbstractEntityView<T extends StructuredViewer> extends ViewPart{

	private T viewer;
	
	protected abstract T createViewer(Composite parent);
	protected abstract Object getInput(DataProvider dp);

	@Override
	public void createPartControl(Composite parent) {
		viewer = createViewer(parent);
		viewer.setContentProvider(getContentProvider());
		viewer.setLabelProvider(new EntityTableLabelProvider());
		ObservableValue<DataProvider> dpObs = ConfettiPlugin.getDefault().getDataProvider();
		dpObs.attachListener(new ObservableListener<DataProvider>() {
			
			@Override
			public void valueChanged(DataProvider oldDp, DataProvider newDp) {
				viewer.setInput(getNullSafeInput(newDp));
			}

		});
		getSite().setSelectionProvider(viewer);
		viewer.setInput(getNullSafeInput(dpObs.getValue()));
	}
	private Object getNullSafeInput(DataProvider newDp) {
		return newDp == null ? null : getInput(newDp);
	}

	
	protected IContentProvider getContentProvider() { return new ArrayContentProvider(); }
	@Override public void setFocus() { viewer.getControl().setFocus(); }
	
}
