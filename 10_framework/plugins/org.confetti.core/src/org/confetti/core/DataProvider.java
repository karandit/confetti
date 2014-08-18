package org.confetti.core;

import java.util.List;

import org.confetti.observable.ObservableList;

public interface DataProvider extends Nameable {

	ObservableList<Subject> getSubjects();
	ObservableList<Teacher> getTeachers();
	ObservableList<StudentGroup> getStudentGroups();
	ObservableList<Room> getRooms();
	ObservableList<Day> getDays();
	ObservableList<Hour> getHours();
	
	Subject addSubject(String name);
	Teacher addTeacher(String name);
	//TODO: StudentGroup addStudentGroup(StudentGroup parent, String name);
	Room addRoom(String name);
	void setDays(List<String> days);
	void setHours(List<String> hours);
	
}
