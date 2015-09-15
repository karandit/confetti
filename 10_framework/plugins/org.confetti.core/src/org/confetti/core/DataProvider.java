package org.confetti.core;

import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;

public interface DataProvider extends Nameable, Constraintable, DataPersister {
    
    String getInformation();
	ObservableValue<String> getComment();

	ObservableList<Subject> getSubjects();
	ObservableList<Teacher> getTeachers();
	ObservableList<StudentGroup> getStudentGroups();
	ObservableList<Room> getRooms();
	ObservableList<Building> getBuildings();
	ObservableList<Day> getDays();
	ObservableList<Hour> getHours();
	ObservableList<Assignment> getAssignments();
	ObservableList<Tag> getTags();
	ObservableValue<Iterable<SolutionSlot>> getSolution();
	
}
