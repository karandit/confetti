package org.confetti.rcp.commands;

import static java.util.Optional.ofNullable;
import static org.confetti.rcp.wizards.WizardUtil.watchWizardDialog;

import java.util.List;

import org.confetti.core.DataProvider;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.extensions.ExportWizardDescr;
import org.confetti.rcp.extensions.ExportWizardFactory;
import org.confetti.rcp.extensions.ExportWizardRegistry;
import org.confetti.rcp.nls.Messages;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;

/**
 * @author Gabor Bubla
 */
public class ExportTimetableCommand extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        List<ExportWizardDescr> extensions = ExportWizardRegistry.INSTANCE.getExtensions();
        
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        ListDialog dlg = new ListDialog(shell);
        dlg.setContentProvider(new ArrayContentProvider());
        dlg.setLabelProvider(new LabelProvider());
        dlg.setTitle(Messages.ExportTimetableCommand_Title);
        dlg.setMessage("Choose an exporter");
        dlg.setInput(extensions); 
        if (Window.OK != dlg.open()) {
            return null;
        }
        Object[] selected = dlg.getResult();
        if (selected == null || selected.length == 0) {
            return null;
        }
        ExportWizardDescr selectedDescr = (ExportWizardDescr) selected[0];
        ExportWizardFactory wizardFactory = selectedDescr.getWizardFactory();
        DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
        WizardDialog dialog = new WizardDialog(shell, wizardFactory.createWizard(dp));
        watchWizardDialog(dialog);
        //TODO: title doesn't showing
        dialog.setTitle("Export");
        dialog.open();
        
        return null;
    }

    @Override
    public boolean isEnabled() {
    	return ofNullable(ConfettiPlugin.getDefault().getDataProvider().getValue())
	    	.flatMap(dp -> ofNullable(dp.getSolution().getValue()))
	    	.isPresent();
    }
    
}
