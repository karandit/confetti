package org.confetti.dummy;

import static java.util.Arrays.asList;

import java.util.List;

import org.confetti.core.Assignment;
import org.confetti.core.Constraint;
import org.confetti.core.ConstraintAttributes;
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

public class DataProviderImpl implements DataProvider {

	private ValueMutator<String> instName = new ValueMutator<>();
	private ListMutator<Subject> subjects;
	private ListMutator<Teacher> teachers;
	private ListMutator<StudentGroup> studentGroups;
	private ListMutator<Room> rooms;
	private ListMutator<Day> days;
	private ListMutator<Hour> hours;
	private ListMutator<Assignment> assignments;
	private ListMutator<Constraint> constraints;
	private ValueMutator<Iterable<SolutionSlot>> solution;
	
	public DataProviderImpl() {
		this.subjects = new ListMutator<>();
		this.teachers = new ListMutator<>();
		this.studentGroups = new ListMutator<>();
		this.rooms = new ListMutator<>();
		this.days = new ListMutator<>();
		this.hours = new ListMutator<>();
		this.assignments = new ListMutator<>();
		this.constraints = new ListMutator<>();
		this.solution = new ValueMutator<>();
		init();
	}

	private void init() {
		instName.setValue(this, "Test institute");
		
		days.addItem(new DayImpl("monday"));
		days.addItem(new DayImpl("tuesday"));
		days.addItem(new DayImpl("wednesday"));
		days.addItem(new DayImpl("thursday"));
		days.addItem(new DayImpl("friday"));
		hours.addItem(new HourImpl("8:00"));
		hours.addItem(new HourImpl("10:00"));
		hours.addItem(new HourImpl("12:00"));
		hours.addItem(new HourImpl("14:00"));
		hours.addItem(new HourImpl("16:00"));
		
		Subject subjMatek = new SubjectImpl("Math");
		Subject subjInfo = new SubjectImpl("Computer science");
		addSubject(subjMatek);
		addSubject(new SubjectImpl("Literatute"));
		addSubject(subjInfo);
		
		Teacher teacher1 = new TeacherImpl("Smith");
		addTeacher(teacher1);
		addTeacher(new TeacherImpl("Tailor"));
		
		
		
		StudentGroupImpl group1721 = new StudentGroupImpl("1721");
        studentGroups.addItem(group1721);
		StudentGroupImpl group1721_1 = new StudentGroupImpl("1");
		group1721.addChild(group1721_1);
		group1721_1.addChild(new StudentGroupImpl("A"));
		group1721_1.addChild(new StudentGroupImpl("B"));
		group1721.addChild(new StudentGroupImpl("2"));

		StudentGroupImpl group2 = new StudentGroupImpl("1731");
        studentGroups.addItem(group2);
        
		addRooms(asList("Room_1", "Room_2"));

		//creating dummy assignment1
		ListMutator<StudentGroup> tmpStudentGroups = new ListMutator<>();
		tmpStudentGroups.addItem(group1721);
		addAssignment(subjMatek, getTeachers().getList(), tmpStudentGroups.getObservableList().getList());
		
		//creating dummy assignment2
		ListMutator<Teacher> tmpTeachers = new ListMutator<>();
		tmpTeachers.addItem(teacher1);
		ListMutator<StudentGroup> tmpStudentGroups2 = new ListMutator<>();
		tmpStudentGroups2.addItem(group2);
		addAssignment(subjInfo, tmpTeachers.getObservableList().getList(), tmpStudentGroups2.getObservableList().getList());
	}

	//-----------------DataProvider's API-------------------------------------------------------------------------------
	@Override public String getInformation() { return "Dummy"; }
	@Override public ObservableValue<String> getName() 					{ return instName.getObservableValue(); }
	@Override public ObservableList<Subject> getSubjects() 				{ return subjects.getObservableList(); }
	@Override public ObservableList<Teacher> getTeachers() 				{ return teachers.getObservableList(); }
	@Override public ObservableList<StudentGroup> getStudentGroups() 	{ return studentGroups.getObservableList(); }
	@Override public ObservableList<Room> getRooms()					{ return rooms.getObservableList(); }
	@Override public ObservableList<Day> getDays() 						{ return days.getObservableList(); }
	@Override public ObservableList<Hour> getHours() 					{ return hours.getObservableList(); }
	@Override public ObservableList<Assignment> getAssignments() 		{ return assignments.getObservableList(); }
	@Override public ObservableList<Constraint> getConstraints() 		{ return constraints.getObservableList(); }
	@Override public ObservableValue<Iterable<SolutionSlot>> getSolution() { return solution.getObservableValue(); }
	
	@Override
	public void addSubjects(List<String> names) {
		for (String name : names) {
		    SubjectImpl subjectImpl = new SubjectImpl(name);
            addSubject(subjectImpl);
        }
	}

    private void addSubject(Subject subject) {
        subjects.addItem(subject);
    }
	
	@Override
	public void addTeachers(List<String> names) {
        for (String name : names) {
            TeacherImpl teacher = new TeacherImpl(name);
            addTeacher(teacher);
        }
	}

    private void addTeacher(Teacher teacher) {
        teachers.addItem(teacher);
    }

	@Override
	public void addStudentGroups(StudentGroup parent, List<String> names) {
		if (parent == null) {
		    for (String name : names) {
		        StudentGroupImpl studentGroup = new StudentGroupImpl(name);
		        studentGroups.addItem(studentGroup);
            }
		} else {
    		StudentGroupImpl parentImpl = (StudentGroupImpl) parent;
            for (String name : names) {
                StudentGroupImpl studentGroup = new StudentGroupImpl(name);
        		parentImpl.addChild(studentGroup);
        		studentGroup.setParent(parentImpl);
            }
        }
	}
	
	@Override
	public void addRooms(List<String> names) {
        for (String name : names) {
            RoomImpl room = new RoomImpl(name);
            rooms.addItem(room);
        }
	}
	
	@Override
	public Assignment addAssignment(Subject subject, Iterable<Teacher> teachers, Iterable<StudentGroup> studentGroups) {
		AssignmentImpl assignment = new AssignmentImpl(subject, teachers, studentGroups);
		assignments.addItem(assignment);
		return assignment;
	}
	
	@Override
	public void setSolution(Iterable<SolutionSlot> solution) {
		this.solution.setValue(this, solution);
	}
	
	@Override
	public void removeSubjects(List<Subject> subjectsToRemove) {
	    for (Subject subject : subjectsToRemove) {
	        this.subjects.removeItem(subject);
        }
	}
	
	@Override
	public void removeTeachers(List<Teacher> teachersToRemove) {
	    for (Teacher teacher : teachersToRemove) {
	        this.teachers.removeItem(teacher);
        }
	}
	
	@Override
	public void removeStudentGroups(List<StudentGroup> studentGroups) {
	    for (StudentGroup studentGroup : studentGroups) {
	        this.studentGroups.removeItem(studentGroup);
        }
	}
	
	@Override
	public void removeRooms(List<Room> rooms) {
	    for (Room room : rooms) {
	        this.rooms.removeItem(room);
        }
	}
	
	@Override
	public void removeAssignment(Assignment assignment) {
	    assignment.getSubject().removeAssignment(assignment);
        for (Teacher teacher : assignment.getTeachers().getList()) {
            teacher.removeAssignment(assignment);
        }
        for (StudentGroup studentGroup : assignment.getStudentGroups().getList()) {
            studentGroup.removeAssignment(assignment);
        }
        assignments.removeItem(assignment);
	}

	@Override
	public void rename(Entity entity, String newName) {
		((EntityImpl) entity).getNameMutator().setValue(entity, newName);
	}

	@Override
	public Constraint addConstraint(String uid, ConstraintAttributes attrs) {
		throw new RuntimeException("Not implemented yet.");
	}

}
