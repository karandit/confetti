package org.confetti.core;

import java.util.List;

import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;

public interface DataProvider extends Nameable {
    //DataProvider
    String getInformation();
    
	ObservableList<Subject> getSubjects();
	ObservableList<Teacher> getTeachers();
	ObservableList<StudentGroup> getStudentGroups();
	ObservableList<Room> getRooms();
	ObservableList<Day> getDays();
	ObservableList<Hour> getHours();
	ObservableList<Assignment> getAssignments();
	ObservableValue<Iterable<SolutionSlot>> getSolution();
	
	//DataPersister
	void addSubjects(List<String> names);
	void addTeachers(List<String> names);
	void addStudentGroups(StudentGroup parent, List<String> names);
	void addRooms(List<String> names);
	Assignment addAssignment(Subject subject, Iterable<Teacher> teachers, Iterable<StudentGroup> studentGroups);
	void setSolution(Iterable<SolutionSlot> solution);
	
	void removeSubjects(List<Subject> subjects);
	void removeTeachers(List<Teacher> teachers);
	void removeStudentGroups(List<StudentGroup> studentGroups);
	void removeRooms(List<Room> rooms);
	void removeAssignment(Assignment assignment);
	
	void rename(Entity entity, String newName);
	
}
