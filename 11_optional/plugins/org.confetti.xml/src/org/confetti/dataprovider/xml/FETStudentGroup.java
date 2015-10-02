package org.confetti.dataprovider.xml;

import org.confetti.core.StudentGroup;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

/**
 * @author Kárándi Tamás
 */
class FETStudentGroup extends FETEntity implements StudentGroup {
	
	private final ValueMutator<Integer> nrOfStudents;
	private final ListMutator<StudentGroup> children = new ListMutator<>();
	private final FETStudentGroup parent;
	
	public FETStudentGroup(String name, int nrOfStudents) {
		this(name, nrOfStudents, null);
	}

	public FETStudentGroup(String name, int nrOfStudents, FETStudentGroup parent) {
		super(name);
		this.nrOfStudents = new ValueMutator<>(this, nrOfStudents);
		this.parent = parent;
	}

	@Override public ObservableValue<Integer> getNrOfStudents() 	{ return nrOfStudents.getObservableValue(); }
	public void addChild(StudentGroup child) 						{ children.addItem(child); }
	@Override public ObservableList<StudentGroup> getChildren() 	{ return children.getObservableList(); }
	@Override public StudentGroup getParent() 						{ return parent; }

}
