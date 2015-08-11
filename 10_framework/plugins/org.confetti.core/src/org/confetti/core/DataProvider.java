package org.confetti.core;

import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;

public interface DataProvider extends Nameable, DataPersister {
    
    String getInformation();
    
	ObservableList<Subject> getSubjects();
	ObservableList<Teacher> getTeachers();
	ObservableList<StudentGroup> getStudentGroups();
	ObservableList<Room> getRooms();
	ObservableList<Day> getDays();
	ObservableList<Hour> getHours();
	ObservableList<Assignment> getAssignments();
	ObservableList<Constraint> getConstraints();
	ObservableValue<Iterable<SolutionSlot>> getSolution();
	
}
