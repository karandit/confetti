package org.confetti.dummy;

import java.util.ArrayList;
import java.util.List;

import org.confetti.core.Assignment;
import org.confetti.core.StudentGroup;


public class StudentGroupImpl implements StudentGroup {

	private final String name;
	private final List<StudentGroup> children = new ArrayList<>();
	private StudentGroup parent;
	private final List<Assignment> assignments = new ArrayList<>();

	public StudentGroupImpl(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public StudentGroup addChild(StudentGroup child) {
		children.add(child);
//		child.parent = this; //TODO:
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
