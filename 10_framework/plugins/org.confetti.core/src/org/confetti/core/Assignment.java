package org.confetti.core;

import java.util.List;

public interface Assignment {

	Subject getSubj();
	List<Teacher> getTeachers();
	List<StudentGroup> getStudentGroups();
	
}
