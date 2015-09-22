package org.confetti.dummy;

import org.confetti.core.StudentGroup;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

public class DummyStudentGroup extends DummyEntity implements StudentGroup {

	private final ValueMutator<Integer> nrOfStudents = new ValueMutator<>(this, 0);
	private final ListMutator<StudentGroup> children = new ListMutator<>();
	private StudentGroup parent;

	public DummyStudentGroup(String name) {
		super(name);
	}
	
	public DummyStudentGroup addChild(DummyStudentGroup child) {
		children.addItem(child);
		child.parent = this;
		return this;
	}
	
	public void setParent(DummyStudentGroup parent) {
		this.parent = parent;
	}

	@Override
	public ObservableValue<Integer> getNrOfStudents() {
		return nrOfStudents.getObservableValue();
	}

	@Override
	public ObservableList<StudentGroup> getChildren() {
		return children.getObservableList();
	}

	@Override
	public StudentGroup getParent() {
		return parent;
	}
	
}
