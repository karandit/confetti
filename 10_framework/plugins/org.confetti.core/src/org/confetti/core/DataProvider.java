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
	ObservableList<Assignment> getAssignments();
	
	Subject addSubject(String name);
	Teacher addTeacher(String name);
	StudentGroup addStudentGroup(StudentGroup parent, String name);
	Room addRoom(String name);
	void setDays(List<String> days);
	void setHours(List<String> hours);
	Assignment addAssignment(Subject subject, Iterable<Teacher> teachers, Iterable<StudentGroup> studentGroups);
	
	void removeSubject(Subject subject);
	void removeTeacher(Teacher teacher);
	//TODO: void removeStudentGroup(StudentGroup studentGroup);
	void removeRoom(Room room);
	
	//TODO remove me
	void rename(Entity entity, String newName);
	
}
