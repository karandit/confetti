package org.confetti.dummy;

import org.confetti.core.EntityVisitor;
import org.confetti.core.StudentGroup;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;

public class StudentGroupImpl extends EntityImpl implements StudentGroup {

	private final ListMutator<StudentGroup> children = new ListMutator<>();
	private StudentGroup parent;

	public StudentGroupImpl(String name) {
		super(name);
	}
	
	public StudentGroupImpl addChild(StudentGroupImpl child) {
		children.addItem(child);
		child.parent = this;
		return this;
	}
	
	public void setParent(StudentGroupImpl parent) {
		this.parent = parent;
	}

	@Override
	public ObservableList<StudentGroup> getChildren() {
		return children.getObservableList();
	}

	@Override
	public StudentGroup getParent() {
		return parent;
	}
	
	@Override
	public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
		return visitor.visitStudentGroup(this, param);
	}

}
