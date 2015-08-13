package org.confetti.dataprovider.xml;

import org.confetti.core.EntityVisitor;
import org.confetti.core.StudentGroup;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;

/**
 * @author Kárándi Tamás
 */
class StudentGroupImpl extends EntityImpl implements StudentGroup {
	
	private final ListMutator<StudentGroup> children = new ListMutator<>();
	
	public StudentGroupImpl(String name) {
		super(name);
	}

	public void addChild(StudentGroup child) 			{ children.addItem(child); }
	@Override public ObservableList<StudentGroup> getChildren() 	{ return children.getObservableList(); }
	@Override public StudentGroup getParent() 			{ return null; }

	@Override
	public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
		return visitor.visitStudentGroup(this, param);
	}
}
