package org.confetti.dummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.confetti.core.DataProvider;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;


public class DataProviderImpl implements DataProvider {
	
	private List<Teacher> teachers;
	private List<Subject> subjects;
	private List<StudentGroup> studentGroups;
	
	public DataProviderImpl() {
		this.teachers = new ArrayList<>();
		this.subjects = new ArrayList<>();
		this.studentGroups = new ArrayList<>();
		init();
	}

	private void init() {
		addSubject("matek");
		addSubject("magyar");
		addSubject("info");
		addTeacher("Tomi bá");
		addTeacher("Jani bá");
		addStudentGroup("evosoft");
		addStudentGroup("elte");
		
		new AssignmentImpl(getSubjects().get(0), getTeachers(), Arrays.asList(getStudentGroups().get(0)));
		new AssignmentImpl(getSubjects().get(2), Arrays.asList(getTeachers().get(1)), Arrays.asList(getStudentGroups().get(1)));
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
	
	private void addTeacher(String name) {
		teachers.add(new TeacherImpl(name));
	}
	
	private void addSubject(String name) {
		subjects.add(new SubjectImpl(name));
	}
	
	private void addStudentGroup(String name) {
		studentGroups.add(new StudentGroupImpl(name));
	}
	
}
