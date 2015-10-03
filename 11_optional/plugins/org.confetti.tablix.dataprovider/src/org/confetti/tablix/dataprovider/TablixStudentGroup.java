package org.confetti.tablix.dataprovider;

import org.confetti.core.StudentGroup;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

/**
 * @author Kárándi Tamás
 */
class TablixStudentGroup extends TablixEntity implements StudentGroup {
	
	private final ValueMutator<Integer> nrOfStudents = new ValueMutator<>(this, 0);
	private final ListMutator<StudentGroup> children = new ListMutator<>();
	private final TablixStudentGroup parent;
	
	public TablixStudentGroup(String name) {
		this(name, null);
	}

	public TablixStudentGroup(String name, TablixStudentGroup parent) {
		super(name);
		this.parent = parent;
	}

	@Override public ObservableValue<Integer> getNrOfStudents() 	{ return nrOfStudents.getObservableValue(); }
	public void addChild(StudentGroup child) 						{ children.addItem(child); }
	@Override public ObservableList<StudentGroup> getChildren() 	{ return children.getObservableList(); }
	@Override public StudentGroup getParent() 						{ return parent; }

}
