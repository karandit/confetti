package org.confetti.core;

import java.util.List;

public interface DataProvider {

	List<Subject> getSubjects();

	List<Teacher> getTeachers();

	List<StudentGroup> getStudentGroups();
	
	List<Room> getRooms();
	
}
