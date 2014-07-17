package org.confetti.rcp.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;

public class NewStudentGroupCommand implements IHandler {

	public final static String ID = "org.confetti.rcp.commands.newStudentGroupCommand";
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		//TODO
		return null;
	}
	
	@Override
	public boolean isEnabled() {
		return false;
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
