package org.confetti.dummy;

import org.confetti.core.Assignment;
import org.confetti.core.Constraint;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;

public class DummyAssignment implements Assignment {

	private final Subject subj;
	private final ListMutator<Teacher> teachers = new ListMutator<>();
	private final ListMutator<StudentGroup> studentGroups = new ListMutator<>();
	private final ListMutator<Constraint> constraints = new ListMutator<>();

	public DummyAssignment(Subject subject, Iterable<Teacher> teachers, Iterable<StudentGroup> studentGroups) {
		this.subj = subject;
		for (Teacher teacher : teachers) {
			this.teachers.addItem(teacher);
		}
		for (StudentGroup studentGroup : studentGroups) {
			this.studentGroups.addItem(studentGroup);
		}

		subject.addAssignment(this);
		for (Teacher teacher : teachers) {
			teacher.addAssignment(this);
		}
		for (StudentGroup studentGroup : studentGroups) {
			studentGroup.addAssignment(this);
		}
	}

	@Override public Subject getSubject() { return subj; }
	@Override public ObservableList<Teacher> getTeachers() { return teachers.getObservableList(); }
	@Override public ObservableList<StudentGroup> getStudentGroups() { return studentGroups.getObservableList(); }
	@Override public Room getRoom() { return null; }
	@Override public ObservableList<Constraint> getConstraints()  { return constraints.getObservableList(); }

}
