package org.confetti.rcp.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class NewSubjectCommand implements IHandler {

	public final static String ID = "org.confetti.rcp.commands.newSubjectCommand";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		IInputValidator validator = new IInputValidator() {
			
			@Override
			public String isValid(String newText) {
				if ("doge".equals(newText)) {
					return null;
				}
				return "only doge allowed";
			}
		};
		InputDialog dialog = new InputDialog(shell, "New Subject", "Enter a unique name", "New subject", validator);
		dialog.open();
		
		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return false;
	}
	
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
	}
	
	@Override
	public void dispose() {
	}

}
