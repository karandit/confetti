package org.confetti.dummy;

import java.util.ArrayList;
import java.util.List;

import org.confetti.core.Assignment;
import org.confetti.core.StudentGroup;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;


public class StudentGroupImpl implements StudentGroup {

	private final ValueMutator<String> name;
	private final List<StudentGroup> children = new ArrayList<>();
	private StudentGroup parent;
	private final List<Assignment> assignments = new ArrayList<>();

	public StudentGroupImpl(String name) {
		this.name = new ValueMutator<>(name);
	}
	
	@Override
	public ObservableValue<String> getName() {
		return name.getObservableValue();
	}

	public StudentGroupImpl addChild(StudentGroupImpl child) {
		children.add(child);
		child.parent = this;
		return this;
	}

	@Override
	public List<StudentGroup> getChildren() {
		return children;
	}

	@Override
	public StudentGroup getParent() {
		return parent;
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
