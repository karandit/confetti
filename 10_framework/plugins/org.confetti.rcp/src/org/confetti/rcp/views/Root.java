package org.confetti.rcp.views;

import org.confetti.core.Assignable;
import org.confetti.core.Assignment;
import org.confetti.core.Constraint;
import org.confetti.core.Constraintable;
import org.confetti.core.DataProvider;
import org.confetti.core.Nameable;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.rcp.ConfettiPlugin;

public enum Root implements Nameable, Assignable, Constraintable {
	All;
	//-------------- Nameable ------------------------------------------------------------------------------------------
	@Override public ObservableValue<String> getName() 				{ return getDataProvider().getName(); }

	//-------------- Assignable ----------------------------------------------------------------------------------------
	@Override public ObservableList<Assignment> getAssignments() 	{ return getDataProvider().getAssignments(); }
	@Override public void addAssignment(Assignment assignment) 		{ }
	@Override public void removeAssignment(Assignment assignment) 	{ }

	//-------------- Constraintable ------------------------------------------------------------------------------------
	@Override
	public ObservableList<Constraint> getConstraints() 				{ return getDataProvider().getConstraints(); }

	//-------------- helpers -------------------------------------------------------------------------------------------
	private DataProvider getDataProvider() {
		return ConfettiPlugin.getDefault().getDataProvider().getValue();
	}

	
}
