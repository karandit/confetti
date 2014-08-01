package org.confetti.rcp.wizards;

import org.confetti.rcp.wizards.pages.IWizardPageNavigatable;
import org.eclipse.jface.dialogs.IPageChangingListener;
import org.eclipse.jface.dialogs.PageChangingEvent;
import org.eclipse.jface.wizard.WizardDialog;

public class WizardUtil {
	
	public static void watchWizardDialog(WizardDialog dialog) {
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
	}

}
