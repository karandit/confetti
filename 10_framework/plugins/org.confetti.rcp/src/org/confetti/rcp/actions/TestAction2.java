package org.confetti.rcp.actions;

import org.confetti.core.DataProvider;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;


public class TestAction2 extends Action {
	
	private final IWorkbenchWindow window;
	
	public TestAction2(String text, IWorkbenchWindow window) {
		this.window = window;
        setText(text);
        // The id is used to refer to the action in a menu or toolbar
		setId(ICommandIds.CMD_OPEN);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.CMD_OPEN);
		setImageDescriptor(org.confetti.rcp.ConfettiPlugin.getImageDescriptor("/icons/sample2.gif"));
	}
	
	public void run() {
		//dummy action for refreshing the views data
		DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
		ConfettiPlugin.getDefault().getDataProvider().setValue(null);
		ConfettiPlugin.getDefault().getDataProvider().setValue(dp);
	}
}
