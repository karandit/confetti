package org.confetti.core;

import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;

public interface StudentGroup extends Entity {

	ObservableValue<Integer> getNrOfStudents();
	ObservableList<StudentGroup> getChildren();
	StudentGroup getParent();

	@Override
	public default <R, P> R accept(NameableVisitor<R, P> visitor, P param) {
		return visitor.visitStudentGroup(this, param);
	}
	
}
