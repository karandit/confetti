package org.confetti.rcp.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;

/**
 * @author Gabor Bubla
 */
public class ResetPerspectiveCommand extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().resetPerspective();
        
        return null;
    }

}
