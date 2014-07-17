package org.confetti.rcp.actions;

import org.confetti.dummy.DataProviderImpl;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;


public class TestAction1 extends Action {

    private final IWorkbenchWindow window;

    public TestAction1(String text, IWorkbenchWindow window) {
        super(text);
        this.window = window;
        // The id is used to refer to the action in a menu or toolbar
        setId(ICommandIds.CMD_OPEN_MESSAGE);
        // Associate the action with a pre-defined command, to allow key bindings.
        setActionDefinitionId(ICommandIds.CMD_OPEN_MESSAGE);
        setImageDescriptor(org.confetti.rcp.ConfettiPlugin.getImageDescriptor("/icons/sample3.gif"));
    }

    @Override
    public void run() {
    	ConfettiPlugin.getDefault().getDataProvider().setValue(new DataProviderImpl());
    }
}