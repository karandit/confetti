package org.confetti.dummy;

import java.util.Arrays;
import java.util.List;

import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Entity;
import org.confetti.core.Hour;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

public class DataProviderImpl implements DataProvider {

	private ListMutator<Subject> subjects;
	private ListMutator<Teacher> teachers;
	private ListMutator<StudentGroup> studentGroups;
	private ListMutator<Room> rooms;
	private ListMutator<Day> days;
	private ListMutator<Hour> hours;
	private ValueMutator<String> instName = new ValueMutator<>();
	
	public DataProviderImpl() {
		this.subjects = new ListMutator<>();
		this.teachers = new ListMutator<>();
		this.studentGroups = new ListMutator<>();
		this.rooms = new ListMutator<>();
		this.days = new ListMutator<>();
		this.hours = new ListMutator<>();
		init();
	}

	private void init() {
		instName.setValue(this, "Test institute");
		Subject subjMatek = addSubject("Math");
		addSubject("Literatute");
		Subject subjInfo = addSubject("Computer science");
		
		Teacher teacher1 = addTeacher("Smith");
		addTeacher("Tailor");
		
		StudentGroupImpl group1721 = addStudentGroup("1721");
		StudentGroupImpl group1721_1 = new StudentGroupImpl("1");
		group1721.addChild(group1721_1);
		group1721_1.addChild(new StudentGroupImpl("A"));
		group1721_1.addChild(new StudentGroupImpl("B"));
		group1721.addChild(new StudentGroupImpl("2"));

		StudentGroupImpl group2 = addStudentGroup("1731");

		Room room1 = addRoom("Room_1");
		Room room2 = addRoom("Room_2");

		new AssignmentImpl(
				subjMatek, 
				getTeachers().getList(), 
				Arrays.<StudentGroup>asList(group1721), 
				room2
			);
		new AssignmentImpl(
				subjInfo, 
				Arrays.<Teacher>asList(teacher1), 
				Arrays.<StudentGroup>asList(group2), 
				room1
			);
	}


	@Override public ObservableList<Teacher> getTeachers() { return teachers.getObservableList(); }
	@Override public ObservableList<Subject> getSubjects() { return subjects.getObservableList(); }
	@Override public ObservableList<StudentGroup> getStudentGroups() { return studentGroups.getObservableList(); }
	@Override public ObservableList<Room> getRooms() { return rooms.getObservableList(); }
	@Override public ObservableList<Day> getDays() { return days.getObservableList(); }
	@Override public ObservableList<Hour> getHours() { return hours.getObservableList(); }
	@Override public ObservableValue<String> getName() { return instName.getObservableValue(); }
	
	@Override
	public Subject addSubject(String name) {
		SubjectImpl subject = new SubjectImpl(name);
		subjects.addItem(subject);
		return subject;
	}
	
	@Override
	public Teacher addTeacher(String name) {
		TeacherImpl teacher = new TeacherImpl(name);
		teachers.addItem(teacher);
		return teacher;
	}

//	@Override
	public StudentGroupImpl addStudentGroup(String name) {
		StudentGroupImpl studentGroup = new StudentGroupImpl(name);
		studentGroups.addItem(studentGroup);
		return studentGroup;
	}
	
	@Override
	public Room addRoom(String name) {
		RoomImpl room = new RoomImpl(name);
		rooms.addItem(room);
		return room;
	}
	
	@Override
	public void removeSubject(Subject subject) {
		subjects.removeItem(subject);
	}
	
	@Override
	public void removeTeacher(Teacher teacher) {
		teachers.removeItem(teacher);
	}
	
	@Override
	public void removeRoom(Room room) {
		rooms.removeItem(room);
	}

	@Override public void setDays(List<String> days) {  }
	@Override public void setHours(List<String> hours) {  }

	@Override
	public void rename(Entity entity, String newName) {
		((EntityImpl) entity).getNameMutator().setValue(entity, newName);
	}

}
