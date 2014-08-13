package org.confetti.dummy;

import java.util.ArrayList;
import java.util.List;

import org.confetti.core.Assignment;
import org.confetti.core.Room;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

public class RoomImpl implements Room{

	private final ValueMutator<String> name;
	private final List<Assignment> assignments = new ArrayList<>();
	
	public RoomImpl(String name) {
		this.name = new ValueMutator<>(name);
	}
	
	@Override
	public ObservableValue<String> getName() {
		return name.getObservableValue();
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
