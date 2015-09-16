package org.confetti.core;

import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;

/**
 * @author K�r�ndi Tam�s
 */
public interface Assignment extends Constraintable {
	
	ObservableValue<Integer> getDuration();
	Subject getSubject();
	ObservableList<Teacher> getTeachers();
	ObservableList<StudentGroup> getStudentGroups();
	ObservableList<Tag> getTags();
	
}
