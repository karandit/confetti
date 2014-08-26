package org.confetti.dummy;

import java.util.LinkedList;
import java.util.List;

import org.confetti.core.Assignment;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;

import com.google.common.collect.Iterables;

public class AssignmentImpl implements Assignment {

	private final Subject subj;
	private final List<Teacher> teachers;
	private final List<StudentGroup> studentGroups;
	private final Room room;

	public AssignmentImpl(Subject subj, Iterable<Teacher> teachers, List<StudentGroup> studentGroups, Room room) {
		this.subj = subj;
		this.teachers = new LinkedList<>();
		Iterables.addAll(this.teachers, teachers);
		this.studentGroups = studentGroups;
		this.room = room;

		subj.addAssignment(this);
		for (Teacher teacher : teachers) {
			teacher.addAssignment(this);
		}
		for (StudentGroup studentGroup : studentGroups) {
			studentGroup.addAssignment(this);
		}
		room.addAssignment(this);
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

	@Override
	public Room getRoom() {
		return room;
	}

}
