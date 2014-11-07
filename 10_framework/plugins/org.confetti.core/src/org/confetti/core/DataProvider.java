package org.confetti.core;

import java.util.List;

import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;

public interface DataProvider extends Nameable {

	ObservableList<Subject> getSubjects();
	ObservableList<Teacher> getTeachers();
	ObservableList<StudentGroup> getStudentGroups();
	ObservableList<Room> getRooms();
	ObservableList<Day> getDays();
	ObservableList<Hour> getHours();
	ObservableList<Assignment> getAssignments();
	ObservableValue<Iterable<SolutionSlot>> getSolution();
	
	Subject addSubject(String name);
	Teacher addTeacher(String name);
	StudentGroup addStudentGroup(StudentGroup parent, String name);
	Room addRoom(String name);
	void setDays(List<String> days);
	void setHours(List<String> hours);
	Assignment addAssignment(Subject subject, Iterable<Teacher> teachers, Iterable<StudentGroup> studentGroups);
	void setSolution(Iterable<SolutionSlot> solution);
	
	void removeSubject(Subject subject);
	void removeTeacher(Teacher teacher);
	void removeStudentGroup(StudentGroup studentGroup);
	void removeRoom(Room room);
	void removeAssignment(Assignment assignment, Subject subject, Iterable<Teacher> teachers, Iterable<StudentGroup> studentGroups);
	
	//TODO remove me
	void rename(Entity entity, String newName);
	
}
