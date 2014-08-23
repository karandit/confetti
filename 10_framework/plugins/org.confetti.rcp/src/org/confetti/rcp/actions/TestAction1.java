package org.confetti.rcp.actions;

import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;


public class TestAction1 extends Action {

    public TestAction1(String text, IWorkbenchWindow window) {
        super(text);
        // The id is used to refer to the action in a menu or toolbar
        setId(ICommandIds.TEST1);
        // Associate the action with a pre-defined command, to allow key bindings.
        setActionDefinitionId(ICommandIds.TEST1);
        setImageDescriptor(ConfettiPlugin.getImageDescriptor(ConfettiPlugin.IMG_SAMPLE3));
    }

    @Override
    public void run() {
//    	ConfettiPlugin.getDefault().getDataProvider().setValue(new DataProviderImpl());
    }
}