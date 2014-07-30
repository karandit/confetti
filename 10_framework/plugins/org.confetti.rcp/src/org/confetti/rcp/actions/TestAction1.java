package org.confetti.rcp.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;


public class TestAction1 extends Action {

    public TestAction1(String text, IWorkbenchWindow window) {
        super(text);
        // The id is used to refer to the action in a menu or toolbar
        setId(ICommandIds.CMD_OPEN_MESSAGE);
        // Associate the action with a pre-defined command, to allow key bindings.
        setActionDefinitionId(ICommandIds.CMD_OPEN_MESSAGE);
        setImageDescriptor(org.confetti.rcp.ConfettiPlugin.getImageDescriptor("/icons/sample3.gif"));
    }

    @Override
    public void run() {
//    	ConfettiPlugin.getDefault().getDataProvider().setValue(new DataProviderImpl());
    }
}