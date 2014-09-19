package org.confetti.rcp.actions;

import static org.confetti.rcp.wizards.WizardUtil.watchWizardDialog;

import java.util.LinkedList;
import java.util.List;

import org.confetti.rcp.extensions.NewWizardDescr;
import org.confetti.rcp.extensions.NewWizardFactory;
import org.confetti.rcp.extensions.NewWizardRegistry;
import org.confetti.rcp.wizards.NewTimetableWizard;
import org.confetti.rcp.wizards.models.NewTimetableModel;
import org.confetti.rcp.wizards.models.NewTimetableModel.NewEntryModel;
import org.confetti.rcp.wizards.models.Problem;
import org.confetti.util.Tuple;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;

public class NewWizardAction extends Action {

	public NewWizardAction() {
		super();
		setId("newWizard");
		setText("New...");
	}

	@Override
	public void run() {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		//wizard for getting the Institute name, comment, List of days, List of hours
		NewTimetableModel model = new NewTimetableModel();
		WizardDialog dialog = new WizardDialog(shell, new NewTimetableWizard(model));
		watchWizardDialog(dialog);
		dialog.setTitle("New...");
		if (Window.OK != dialog.open()) {
			return;
		}
		
		//dialog with all the extensions
		ListDialog dlg = new ListDialog(shell);
		dlg.setContentProvider(new ArrayContentProvider());
		dlg.setLabelProvider(new LabelProvider());
		dlg.setTitle("New");
		dlg.setMessage("Choose an input");
		List<NewWizardDescr> extensions = NewWizardRegistry.INSTANCE.getExtensions();
		dlg.setInput(extensions); 
		if (Window.OK != dlg.open()) {
			return;
		}
		Object[] selected = dlg.getResult();
		if (selected == null || selected.length == 0) {
			return;
		}
		//open the selected extension's wizard
		NewWizardDescr selectedDescr = (NewWizardDescr) selected[0];
		NewWizardFactory wizardFactory = selectedDescr.getWizardFactory();
		List<String> days = getEntries(model.getDaysModel());
		List<String> hours = getEntries(model.getHoursModel());
		
		dialog = new WizardDialog(shell, wizardFactory.createWizard(model.getName(), model.getComment(), days, hours));
		watchWizardDialog(dialog);
		dialog.open();
	}

	private List<String> getEntries(NewEntryModel model) {
		List<String> res = new LinkedList<>();
		for (Tuple<String, Problem> tuple : model.getResult()) {
			res.add(tuple.getFirst());
		}
		return res;
	}

}
