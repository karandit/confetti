package org.confetti.dummy;

import java.util.ArrayList;
import java.util.List;

import org.confetti.core.EntityVisitor;
import org.confetti.core.StudentGroup;

public class StudentGroupImpl extends EntityImpl implements StudentGroup {

	private final List<StudentGroup> children = new ArrayList<>();
	private StudentGroup parent;

	public StudentGroupImpl(String name) {
		super(name);
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
	public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
		return visitor.visitStudentGroup(this, param);
	}

}
