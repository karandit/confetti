package org.confetti.rcp.commands;

import static org.confetti.rcp.wizards.WizardUtil.watchWizardDialog;

import java.util.List;

import org.confetti.rcp.extensions.OpenWizardDescr;
import org.confetti.rcp.extensions.OpenWizardFactory;
import org.confetti.rcp.extensions.OpenWizardRegistry;
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
public class OpenWizardCommand extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        List<OpenWizardDescr> extensions = OpenWizardRegistry.INSTANCE.getExtensions();
//      OpenWizardModel model = new OpenWizardModel(extensions);
//      IWizard wizard = new OpenWizard(model);
//      WizardDialog dialog = new WizardDialog(shell, wizard);
//      watchWizardDialog(dialog);
//      dialog.open();
        
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        ListDialog dlg = new ListDialog(shell);
        dlg.setContentProvider(new ArrayContentProvider());
        dlg.setLabelProvider(new LabelProvider());
        dlg.setTitle(Messages.OpenWizardCommand_Title);
        dlg.setMessage(Messages.OpenWizardCommand_Message);
        dlg.setInput(extensions); 
        if (Window.OK != dlg.open()) {
            return null;
        }
        Object[] selected = dlg.getResult();
        if (selected == null || selected.length == 0) {
            return null;
        }
        OpenWizardDescr selectedDescr = (OpenWizardDescr) selected[0];
        OpenWizardFactory wizardFactory = selectedDescr.getWizardFactory();
        WizardDialog dialog = new WizardDialog(shell, wizardFactory.createWizard());
        watchWizardDialog(dialog);
        //TODO: title doesn't showing
        dialog.setTitle(Messages.OpenWizardCommand_Label);
        dialog.open();
        
        return null;
    }

}
