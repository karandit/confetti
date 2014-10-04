package org.confetti.dataprovider.db;

import java.util.List;

import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Entity;
import org.confetti.core.Hour;
import org.confetti.core.Room;
import org.confetti.core.SolutionSlot;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;
import org.hibernate.SessionFactory;

/**
 * @author Gabor Bubla
 */
public class DbDataProvider implements DataProvider {

	//----------------------------- fields -----------------------------------------------------------------------------
	private ValueMutator<String> instName = new ValueMutator<>();
	private ListMutator<Teacher> teachers = new ListMutator<>();
	private ListMutator<Subject> subjects = new ListMutator<>();
	private ListMutator<StudentGroup> stdGroups = new ListMutator<>();
	private ListMutator<Room> rooms = new ListMutator<>();
	private ListMutator<Day> days = new ListMutator<>();
	private ListMutator<Hour> hours = new ListMutator<>();
	private ListMutator<Assignment> assignments = new ListMutator<>();
	private ValueMutator<Iterable<SolutionSlot>> solution = new ValueMutator<>();
	
	private final SessionFactory sFact;

	public DbDataProvider(SessionFactory sFact) {
		this.sFact = sFact;
	}
	
	//----------------------------- DataProvider's API -----------------------------------------------------------------
	@Override public ObservableValue<String> getName() 					{ return instName.getObservableValue(); }
	@Override public ObservableList<Teacher> getTeachers() 				{ return teachers.getObservableList(); }
	@Override public ObservableList<Subject> getSubjects() 				{ return subjects.getObservableList(); }
	@Override public ObservableList<StudentGroup> getStudentGroups() 	{ return stdGroups.getObservableList(); }
	@Override public ObservableList<Room> getRooms() 					{ return rooms.getObservableList(); }
	@Override public ObservableList<Day> getDays() 						{ return days.getObservableList(); }
	@Override public ObservableList<Hour> getHours() 					{ return hours.getObservableList(); }
	@Override public ObservableList<Assignment> getAssignments() 		{ return assignments.getObservableList(); }
	@Override public ObservableValue<Iterable<SolutionSlot>> getSolution() { return solution.getObservableValue(); }
	
	@Override public Subject addSubject(String name) { return null; }
	@Override public Teacher addTeacher(String name) { return null; }
	@Override public StudentGroup addStudentGroup(StudentGroup parent, String name) { return null; }
	@Override public Room addRoom(String name) { return null; }
	@Override public void setDays(List<String> days) { }
	@Override public void setHours(List<String> hours) { }
	@Override public Assignment addAssignment(Subject subject, Iterable<Teacher> teachers, Iterable<StudentGroup> studentGroups) { return null; }
	@Override public void setSolution(Iterable<SolutionSlot> solution) { }
	@Override public void removeSubject(Subject subject) { }
	@Override public void removeTeacher(Teacher teacher) { }
	@Override public void removeRoom(Room room) { }
	@Override public void rename(Entity entity, String newName) { }

}
