package org.confetti.dummy;

import java.util.List;

import org.confetti.core.Assignment;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;


public class AssignmentImpl implements Assignment {

	private final Subject subj;
	private final List<Teacher> teachers;
	private final List<StudentGroup> studentGroups;

	public AssignmentImpl(Subject subj, List<Teacher> teachers, List<StudentGroup> studentGroups) {
		this.subj = subj;
		this.teachers = teachers;
		this.studentGroups = studentGroups;
		
		subj.addAssignment(this);
		for (Teacher teacher : teachers) {
			teacher.addAssignment(this);
		}
		for (StudentGroup studentGroup : studentGroups) {
			studentGroup.addAssignment(this);
		}
	}

	public Subject getSubj() {
		return subj;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public List<StudentGroup> getStudentGroups() {
		return studentGroups;
	}
	
}
