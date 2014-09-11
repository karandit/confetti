package org.confetti.core;

import org.confetti.observable.ObservableList;

public interface Assignable {

	void addAssignment(Assignment assignment);
	ObservableList<Assignment> getAssignments();
	
}
