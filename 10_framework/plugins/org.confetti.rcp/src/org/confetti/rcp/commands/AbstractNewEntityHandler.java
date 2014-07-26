package org.confetti.rcp.commands;

import java.util.LinkedList;
import java.util.List;

import org.confetti.core.Entity;
import org.confetti.rcp.wizards.IWizardPageNavigatable;
import org.confetti.rcp.wizards.NewEntityWizard;
import org.confetti.rcp.wizards.NewEntityWizardModel;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.IPageChangingListener;
import org.eclipse.jface.dialogs.PageChangingEvent;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

abstract class AbstractNewEntityHandler<T> extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWizard wizard = new NewEntityWizard(createModel());
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.addPageChangingListener(new IPageChangingListener() {
			
			@Override
			public void handlePageChanging(PageChangingEvent event) {
				if (event.getCurrentPage() instanceof IWizardPageNavigatable) {
					((IWizardPageNavigatable) event.getCurrentPage()).pageHid();
				}
				if (event.getTargetPage() instanceof IWizardPageNavigatable) {
					((IWizardPageNavigatable) event.getTargetPage()).pageShowed();
				}
				
			}
		});
		dialog.open();
		return null;
	}

	protected abstract NewEntityWizardModel<T> createModel();

	@Override public boolean isEnabled() { return true; }
	@Override public void dispose() { }
	
	protected static List<String> getNames(List<? extends Entity> entities) {
		List<String> names = new LinkedList<>();
		for (Entity entity : entities) {
			names.add(entity.getName());
		}
		return names;
	}
}
