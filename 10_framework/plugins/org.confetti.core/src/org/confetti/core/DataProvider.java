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
	Day setDays(List<Day> days);
	Hour setHours(List<Hour> hours);
	
}
