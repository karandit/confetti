package org.confetti.rcp.commands;

import java.util.List;

import org.confetti.rcp.extensions.ConstraintDescr;
import org.confetti.rcp.extensions.ConstraintRegistry;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;

/**
 * @author Gabor Bubla
 */
public class NewConstraintCommand extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        List<ConstraintDescr> constraintsDescr = ConstraintRegistry.INSTANCE.getExtensions();
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        ListDialog dlg = new ListDialog(shell);
        dlg.setContentProvider(new ArrayContentProvider());
        dlg.setLabelProvider(new LabelProvider());
        dlg.setTitle("Open");
        dlg.setMessage("Choose an input");
        dlg.setInput(constraintsDescr); 
        
        if (Window.OK != dlg.open()) {
            return null;
        }
        return null;
    }

}
