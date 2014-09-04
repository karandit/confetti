package org.confetti.core;

import org.confetti.observable.ObservableList;

public interface StudentGroup extends Entity {

	ObservableList<StudentGroup> getChildren();
	StudentGroup getParent();
	
}
