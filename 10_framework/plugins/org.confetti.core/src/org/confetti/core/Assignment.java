package org.confetti.core;

import java.util.Optional;

import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;

/**
 * @author Kárándi Tamás
 */
public interface Assignment extends Constraintable {
	
	ObservableValue<Integer> getDuration();
	ObservableValue<Integer> getNrOfStudents();
	Subject getSubject();
	ObservableList<Teacher> getTeachers();
	ObservableList<StudentGroup> getStudentGroups();
	ObservableList<Tag> getTags();
	ObservableValue<Optional<AssignmentGroup>> getGroup();
	
}
