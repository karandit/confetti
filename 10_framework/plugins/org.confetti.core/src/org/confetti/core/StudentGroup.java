package org.confetti.core;

import java.util.List;

public interface StudentGroup extends Entity, Assignable {

	List<StudentGroup> getChildren();

	StudentGroup getParent();
	
}
