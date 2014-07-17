package org.confetti.dummy;

import java.util.ArrayList;
import java.util.List;

import org.confetti.core.Assignment;
import org.confetti.core.Room;

public class RoomImpl implements Room{

	private final String name;
	private final List<Assignment> assignments = new ArrayList<>();
	
	public RoomImpl(String name) {
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
