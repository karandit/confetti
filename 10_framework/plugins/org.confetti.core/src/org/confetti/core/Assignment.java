package org.confetti.core;

import org.confetti.observable.ObservableList;

public interface Assignment extends Constraintable {

	Subject getSubject();
	ObservableList<Teacher> getTeachers();
	ObservableList<StudentGroup> getStudentGroups();
	Room getRoom();
	
}
