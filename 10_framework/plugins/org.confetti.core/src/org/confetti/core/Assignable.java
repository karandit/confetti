package org.confetti.core;

import java.util.List;

public interface Assignable {

	void addAssignment(Assignment assignment);
	
	List<Assignment> getAssignments();
	
}
