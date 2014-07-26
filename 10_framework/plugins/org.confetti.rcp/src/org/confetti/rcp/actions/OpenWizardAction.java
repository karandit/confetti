package org.confetti.rcp.actions;

import static org.confetti.rcp.wizards.WizardUtil.watchWizardDialog;

import java.util.List;

import org.confetti.rcp.extensions.OpenWizardDescr;
import org.confetti.rcp.extensions.OpenWizardRegistry;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;

public class OpenWizardAction extends Action {

	public OpenWizardAction() {
		super();
		setId("openWizard");
		setText("Open...");
	}

	@Override
	public void run() {
		List<OpenWizardDescr> extensions = OpenWizardRegistry.INSTANCE.getExtensions();
//		OpenWizardModel model = new OpenWizardModel(extensions);
//		IWizard wizard = new OpenWizard(model);
//		WizardDialog dialog = new WizardDialog(shell, wizard);
//		watchWizardDialog(dialog);
//		dialog.open();
		
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		ListDialog dlg = new ListDialog(shell);
		dlg.setContentProvider(new ArrayContentProvider());
		dlg.setLabelProvider(new LabelProvider());
		dlg.setTitle("Open");
		dlg.setMessage("Choose an input");
		dlg.setInput(extensions); 
		if (Window.OK != dlg.open()) {
			return;
		}
		Object[] selected = dlg.getResult();
		if (selected == null || selected.length == 0) {
			return;
		}
		OpenWizardDescr selectedDescr = (OpenWizardDescr) selected[0];
		IWizard wizard = selectedDescr.getWizard();
		WizardDialog dialog = new WizardDialog(shell, wizard);
		watchWizardDialog(dialog);
		dialog.open();
	}

}
