package org.confetti.fet.engine;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.net.URL;
import java.util.Optional;

import org.confetti.core.DataPersister;
import org.confetti.core.DataProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * @author Gabor Bubla
 */
public class FETEngineWizard extends Wizard {

	private final DataProvider mDataProvider;
	private final DataPersister mDataPersister;
	private FETEngineWizardPage mConsolePage;
	
	public FETEngineWizard(DataProvider dataProvider, DataPersister dataPersister) {
		this.mDataProvider = dataProvider;
		this.mDataPersister = dataPersister;
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		setWindowTitle("Generate");
		mConsolePage = new FETEngineWizardPage("FET Wizard Page");
		addPage(mConsolePage);
	}
	
	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		IWizardContainer container = getContainer();
		if (container instanceof WizardDialog) {
			WizardDialog dlg = (WizardDialog) container;
			Composite buttonBar = (Composite) dlg.buttonBar;
			Composite composite = (Composite) buttonBar.getChildren()[1];
			findButton(composite, IDialogConstants.FINISH_ID).ifPresent(finishButton -> finishButton.setText("Generate"));
		}
	}
	
	@Override
	public boolean performFinish() {
		try {
			mConsolePage.setReadOnly();
			URL copyingUrl = FETEngineWizard.class.getResource("COPYING");
			if (copyingUrl == null) {
				MessageDialog.openWarning(this.getShell(), "Warning", "Unfortunatelly FET client not found.");
				return true;
			}
			
			FETRunnable fetRunnable = new FETRunnable(mDataProvider, copyingUrl, mConsolePage.isVerbose());
			Display display = this.getShell().getDisplay();
			fetRunnable.attachPrintListener(event -> display.asyncExec(() -> mConsolePage.print((String) event.data)));
			getContainer().run(true, false, fetRunnable);
			if (fetRunnable.getSolution() != null) {
				mDataPersister.setSolution(fetRunnable.getSolution());
			}
			changeButtons();
			return false;
		} catch (Throwable e) {
			MessageDialog.openError(this.getShell(), "Error", e.getLocalizedMessage());
	    	mConsolePage.print(e.getLocalizedMessage());
			changeButtons();
			return false;
		}
	}

	private void changeButtons() {
		IWizardContainer container = getContainer();
		if (container instanceof WizardDialog) {
			WizardDialog dlg = (WizardDialog) container;
			Composite buttonBar = (Composite) dlg.buttonBar;
			Composite composite = (Composite) buttonBar.getChildren()[1];
			
			findButton(composite, IDialogConstants.FINISH_ID).ifPresent(finishButton -> finishButton.setEnabled(false));
			findButton(composite, IDialogConstants.CANCEL_ID).ifPresent(cancelButton -> cancelButton.setText("Close"));
		}
	}
	
	private Optional<Button> findButton(Composite comp, Integer buttonId) {
		for (Control ctrl : comp.getChildren()) {
			if (ctrl instanceof Button && buttonId.equals(ctrl.getData())) {
				return of((Button) ctrl);
			}
		}
		return empty();
	}
}
