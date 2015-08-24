package org.confetti.rcp.views;

import org.confetti.core.Assignable;
import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;
import org.confetti.core.Nameable;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.rcp.ConfettiPlugin;

public enum Root implements Nameable, Assignable {
	All;
	
	@Override public ObservableValue<String> getName() 				{ return getDataProvider().getName(); }

	@Override public ObservableList<Assignment> getAssignments() 	{ return getDataProvider().getAssignments(); }
	@Override public void addAssignment(Assignment assignment) 		{ }
	@Override public void removeAssignment(Assignment assignment) 	{ }

	//-------------- helpers -------------------------------------------------------------------------------------------
	private DataProvider getDataProvider() {
		return ConfettiPlugin.getDefault().getDataProvider().getValue();
	}
	
}
