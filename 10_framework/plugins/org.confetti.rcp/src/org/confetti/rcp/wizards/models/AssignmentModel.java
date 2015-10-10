package org.confetti.rcp.wizards.models;

import java.util.List;

import org.confetti.core.DataProvider;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;

public class AssignmentModel {
	
	private DataProvider dp;
	private List<Subject> subjects;
	private List<StudentGroup> studentGroups;
	private List<Teacher> teachers;

	public AssignmentModel(DataProvider dp) {
		this.dp = dp;
	}

	public DataProvider getDataProvider() 				{ return dp; }
	
	public List<Subject> getSubjects() 					{ return subjects; }
	public void setSubjects(List<Subject> subjects) 	{ this.subjects = subjects; }

	public List<Teacher> getTeachers() 					{ return teachers; }
	public void setTeachers(List<Teacher> teachers) 	{ this.teachers = teachers; }

	public List<StudentGroup> getStudentGroups() 		{ return studentGroups; }
	public void setStudentGroups(List<StudentGroup> sg) { this.studentGroups = sg; }

}
