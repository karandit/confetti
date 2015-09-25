package org.confetti.rcp.commands;

import static org.confetti.rcp.wizards.WizardUtil.watchWizardDialog;

import java.util.List;

import org.confetti.core.DataProvider;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.extensions.NewWizardDescr;
import org.confetti.rcp.extensions.NewWizardFactory;
import org.confetti.rcp.extensions.NewWizardRegistry;
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
public class SaveAsCommand extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        
        //dialog with all the extensions
        ListDialog dlg = new ListDialog(shell);
        dlg.setContentProvider(new ArrayContentProvider());
        dlg.setLabelProvider(new LabelProvider());
        dlg.setTitle(Messages.SaveAsCommand_Title);
        dlg.setMessage(Messages.SaveAsCommand_Message);
        List<NewWizardDescr> extensions = NewWizardRegistry.INSTANCE.getExtensions();
        dlg.setInput(extensions); 
        if (Window.OK != dlg.open()) {
            return null;
        }
        Object[] selected = dlg.getResult();
        if (selected == null || selected.length == 0) {
            return null;
        }
        //open the selected extension's wizard
        NewWizardDescr selectedDescr = (NewWizardDescr) selected[0];
        NewWizardFactory wizardFactory = selectedDescr.getWizardFactory();
        
        DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
        WizardDialog dialog = new WizardDialog(shell, wizardFactory.createWizard(dp));
        watchWizardDialog(dialog);
        dialog.open();
        
        return null;
    }

    @Override
    public boolean isEnabled() {
        return ConfettiPlugin.getDefault().getDataProvider().getValue() == null ? false : true;
    }

}
