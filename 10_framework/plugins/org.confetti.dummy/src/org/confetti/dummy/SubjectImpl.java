package org.confetti.dummy;

import java.util.ArrayList;
import java.util.List;

import org.confetti.core.Assignment;
import org.confetti.core.Subject;


public class SubjectImpl implements Subject {

	private final String name;
	private final List<Assignment> assignments = new ArrayList<>();
	
	public SubjectImpl(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void addAssignment(Assignment assignment) {
		assignments.add(assignment);
	}

	@Override
	public List<Assignment> getAssignments() {
		return assignments;
	}

	
}
