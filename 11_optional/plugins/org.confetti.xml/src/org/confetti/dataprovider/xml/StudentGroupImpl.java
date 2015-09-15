package org.confetti.dataprovider.xml;

import org.confetti.core.EntityVisitor;
import org.confetti.core.StudentGroup;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

/**
 * @author Kárándi Tamás
 */
class StudentGroupImpl extends EntityImpl implements StudentGroup {
	
	private final ValueMutator<Integer> nrOfStudents;
	private final ListMutator<StudentGroup> children = new ListMutator<>();
	private final StudentGroupImpl parent;
	
	public StudentGroupImpl(String name, int nrOfStudents) {
		this(name, nrOfStudents, null);
	}

	public StudentGroupImpl(String name, int nrOfStudents, StudentGroupImpl parent) {
		super(name);
		this.nrOfStudents = new ValueMutator<>(this, nrOfStudents);
		this.parent = parent;
	}

	@Override public ObservableValue<Integer> getNrOfStudents() 	{ return nrOfStudents.getObservableValue(); }
	public void addChild(StudentGroup child) 						{ children.addItem(child); }
	@Override public ObservableList<StudentGroup> getChildren() 	{ return children.getObservableList(); }
	@Override public StudentGroup getParent() 						{ return parent; }

	@Override
	public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
		return visitor.visitStudentGroup(this, param);
	}
}
