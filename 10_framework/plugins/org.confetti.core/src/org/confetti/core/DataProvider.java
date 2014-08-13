package org.confetti.core;

import java.util.List;

public interface DataProvider {

	List<Subject> getSubjects();
	List<Teacher> getTeachers();
	List<StudentGroup> getStudentGroups();
	List<Room> getRooms();
	List<Day> getDays();
	List<Hour> getHours();
	
	Subject addSubject(String name);
	Teacher addTeacher(String name);
	//TODO: StudentGroup addStudentGroup(StudentGroup parent, String name);
	Room addRoom(String name);
	void setDays(List<String> days);
	void setHours(List<String> hours);
	
}
