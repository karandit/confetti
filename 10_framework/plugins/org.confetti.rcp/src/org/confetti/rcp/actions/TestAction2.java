package org.confetti.rcp.actions;

import org.confetti.core.DataProvider;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;


public class TestAction2 extends Action {
	
//	private final IWorkbenchWindow window;
	
	public TestAction2(String text, IWorkbenchWindow window) {
//		this.window = window;
        setText(text);
        // The id is used to refer to the action in a menu or toolbar
		setId(ICommandIds.TEST2);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.TEST2);
		setImageDescriptor(org.confetti.rcp.ConfettiPlugin.getImageDescriptor("/icons/sample2.gif"));
	}
	
	public void run() {
		//dummy action for refreshing the views data
		DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
		ConfettiPlugin.getDefault().setDataProvider(null);
		ConfettiPlugin.getDefault().setDataProvider(dp);
	}
}
