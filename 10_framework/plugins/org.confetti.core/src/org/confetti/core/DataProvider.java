package org.confetti.core;

import java.util.List;

public interface DataProvider {

	List<Teacher> getTeachers();

	List<Subject> getSubjects();

	List<StudentGroup> getStudentGroups();
	
}
