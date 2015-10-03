package org.confetti.dummy;

import static java.util.Arrays.asList;

import java.util.List;

import org.confetti.core.Assignment;
import org.confetti.core.Building;
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
import org.confetti.core.Tag;
import org.confetti.core.Teacher;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

public class DummyDataProvider implements DataProvider {

	private final ValueMutator<String> instName = new ValueMutator<>();
	private final ValueMutator<String> instComment = new ValueMutator<>();
	private final ListMutator<Subject> subjects = new ListMutator<>();
	private final ListMutator<Teacher> teachers = new ListMutator<>();
	private final ListMutator<StudentGroup> studentGroups = new ListMutator<>();
	private final ListMutator<Room> rooms = new ListMutator<>();
	private final ListMutator<Building> buildings = new ListMutator<>();
	private final ListMutator<Day> days = new ListMutator<>();
	private final ListMutator<Hour> hours = new ListMutator<>();
	private final ListMutator<Assignment> assignments = new ListMutator<>();
	private final ListMutator<Constraint> constraints = new ListMutator<>();
	private final ListMutator<Tag> tags = new ListMutator<>();
	private final ValueMutator<Iterable<SolutionSlot>> solution = new ValueMutator<>();
	
	public DummyDataProvider() {
		init();
	}

	private void init() {
		instName.setValue(this, "Test institute");
		instComment.setValue(this, "a half implemented data provider");
		
		days.addItem(new DummyDay("monday"));
		days.addItem(new DummyDay("tuesday"));
		days.addItem(new DummyDay("wednesday"));
		days.addItem(new DummyDay("thursday"));
		days.addItem(new DummyDay("friday"));
		hours.addItem(new DummyHour("8:00"));
		hours.addItem(new DummyHour("10:00"));
		hours.addItem(new DummyHour("12:00"));
		hours.addItem(new DummyHour("14:00"));
		hours.addItem(new DummyHour("16:00"));
		
		Subject subjMatek = new DummySubject("Math");
		Subject subjInfo = new DummySubject("Computer science");
		addSubject(subjMatek);
		addSubject(new DummySubject("Literatute"));
		addSubject(subjInfo);
		
		Teacher teacher1 = new DummyTeacher("Smith");
		addTeacher(teacher1);
		addTeacher(new DummyTeacher("Tailor"));
		
		
		
		DummyStudentGroup group1721 = new DummyStudentGroup("1721");
        studentGroups.addItem(group1721);
		DummyStudentGroup group1721_1 = new DummyStudentGroup("1");
		group1721.addChild(group1721_1);
		group1721_1.addChild(new DummyStudentGroup("A"));
		group1721_1.addChild(new DummyStudentGroup("B"));
		group1721.addChild(new DummyStudentGroup("2"));

		DummyStudentGroup group2 = new DummyStudentGroup("1731");
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
	@Override public ObservableValue<String> getComment() 				{ return instComment.getObservableValue(); }
	@Override public ObservableList<Subject> getSubjects() 				{ return subjects.getObservableList(); }
	@Override public ObservableList<Teacher> getTeachers() 				{ return teachers.getObservableList(); }
	@Override public ObservableList<StudentGroup> getStudentGroups() 	{ return studentGroups.getObservableList(); }
	@Override public ObservableList<Room> getRooms()					{ return rooms.getObservableList(); }
	@Override public ObservableList<Building> getBuildings() 			{ return buildings.getObservableList(); }
	@Override public ObservableList<Day> getDays() 						{ return days.getObservableList(); }
	@Override public ObservableList<Hour> getHours() 					{ return hours.getObservableList(); }
	@Override public ObservableList<Assignment> getAssignments() 		{ return assignments.getObservableList(); }
	@Override public ObservableList<Constraint> getConstraints() 		{ return constraints.getObservableList(); }
	@Override public ObservableList<Tag> getTags() 						{ return tags.getObservableList(); }
	@Override public ObservableValue<Iterable<SolutionSlot>> getSolution() { return solution.getObservableValue(); }
	
	@Override
	public void addSubjects(List<String> names) {
		for (String name : names) {
		    DummySubject subjectImpl = new DummySubject(name);
            addSubject(subjectImpl);
        }
	}

    private void addSubject(Subject subject) {
        subjects.addItem(subject);
    }
	
	@Override
	public void addTeachers(List<String> names) {
        for (String name : names) {
            DummyTeacher teacher = new DummyTeacher(name);
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
		        DummyStudentGroup studentGroup = new DummyStudentGroup(name);
		        studentGroups.addItem(studentGroup);
            }
		} else {
    		DummyStudentGroup parentImpl = (DummyStudentGroup) parent;
            for (String name : names) {
                DummyStudentGroup studentGroup = new DummyStudentGroup(name);
        		parentImpl.addChild(studentGroup);
        		studentGroup.setParent(parentImpl);
            }
        }
	}
	
	@Override
	public void addRooms(List<String> names) {
        for (String name : names) {
            DummyRoom room = new DummyRoom(name);
            rooms.addItem(room);
        }
	}
	
	@Override
	public Assignment addAssignment(Subject subject, Iterable<Teacher> teachers, Iterable<StudentGroup> studentGroups) {
		DummyAssignment assignment = new DummyAssignment(subject, teachers, studentGroups);
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
	    ((DummySubject) assignment.getSubject()).removeAssignment(assignment);
        for (Teacher teacher : assignment.getTeachers().getList()) {
            ((DummyTeacher) teacher).removeAssignment(assignment);
        }
        for (StudentGroup studentGroup : assignment.getStudentGroups().getList()) {
            ((DummyStudentGroup) studentGroup).removeAssignment(assignment);
        }
        assignments.removeItem(assignment);
	}

	@Override
	public void rename(Entity entity, String newName) {
		((DummyEntity) entity).getNameMutator().setValue(entity, newName);
	}

	@Override
	public void addConstraint(String uid, ConstraintAttributes attrs) {
		throw new RuntimeException("Not implemented yet.");
	}

	@Override
	public void updateConstraint(Constraint constraint, ConstraintAttributes attrs) {
		throw new RuntimeException("Not implemented yet.");
	}

	@Override
	public void updateInstituteNameAndComment(String newName, String newComment) {
		throw new RuntimeException("Not implemented yet.");
	}

}
