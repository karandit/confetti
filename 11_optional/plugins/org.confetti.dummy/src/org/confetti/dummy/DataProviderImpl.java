package org.confetti.dummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.confetti.core.DataProvider;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;

public class DataProviderImpl implements DataProvider {

	private List<Subject> subjects;
	private List<Teacher> teachers;
	private List<StudentGroup> studentGroups;
	private List<Room> rooms;

	public DataProviderImpl() {
		this.subjects = new ArrayList<>();
		this.teachers = new ArrayList<>();
		this.studentGroups = new ArrayList<>();
		this.rooms = new ArrayList<>();
		init();
	}

	private void init() {
		SubjectImpl subjMatek = addSubject("Math");
		addSubject("Literatute");
		SubjectImpl subjInfo = addSubject("Computer science");
		
		TeacherImpl teacher1 = addTeacher("Smith");
		addTeacher("Tailor");
		
		StudentGroupImpl group1721 = addStudentGroup("1721");
		StudentGroupImpl group1721_1 = new StudentGroupImpl("1");
		group1721.addChild(group1721_1);
		group1721_1.addChild(new StudentGroupImpl("A"));
		group1721_1.addChild(new StudentGroupImpl("B"));
		group1721.addChild(new StudentGroupImpl("2"));

		StudentGroupImpl group2 = addStudentGroup("1731");

		RoomImpl room1 = addRoom("Room_1");
		RoomImpl room2 = addRoom("Room_2");

		new AssignmentImpl(
				subjMatek, 
				getTeachers(), 
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


	@Override
	public List<Teacher> getTeachers() {
		return teachers;
	}

	@Override
	public List<Subject> getSubjects() {
		return subjects;
	}

	@Override
	public List<StudentGroup> getStudentGroups() {
		return studentGroups;
	}
	
	@Override
	public List<Room> getRooms() {
		return rooms;
	}

	private SubjectImpl addSubject(String name) {
		SubjectImpl subject = new SubjectImpl(name);
		subjects.add(subject);
		return subject;
	}
	
	private TeacherImpl addTeacher(String name) {
		TeacherImpl teacher = new TeacherImpl(name);
		teachers.add(teacher);
		return teacher;
	}

	private StudentGroupImpl addStudentGroup(String name) {
		StudentGroupImpl studentGroup = new StudentGroupImpl(name);
		studentGroups.add(studentGroup);
		return studentGroup;
	}

	private RoomImpl addRoom(String name) {
		RoomImpl room = new RoomImpl(name);
		rooms.add(room);
		return room;
	}

}
