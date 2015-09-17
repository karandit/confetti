package org.confetti.dummy;

import java.util.Optional;

import org.confetti.core.Assignment;
import org.confetti.core.AssignmentGroup;
import org.confetti.core.Constraint;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Tag;
import org.confetti.core.Teacher;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

public class DummyAssignment implements Assignment {

    private final ValueMutator<Integer> duration = new ValueMutator<>(this, 1);
    private final Subject subj;
	private final ListMutator<Teacher> teachers = new ListMutator<>();
	private final ListMutator<StudentGroup> studentGroups = new ListMutator<>();
	private final ListMutator<Constraint> constraints = new ListMutator<>();
	private final ListMutator<Tag> tags = new ListMutator<>();
    private final ValueMutator<Optional<AssignmentGroup>> group = new ValueMutator<>(this, Optional.empty());

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

	@Override public ObservableValue<Integer> getDuration() { return duration.getObservableValue(); }
	@Override public Subject getSubject() 								{ return subj; }
	@Override public ObservableList<Teacher> getTeachers() 				{ return teachers.getObservableList(); }
	@Override public ObservableList<StudentGroup> getStudentGroups() 	{ return studentGroups.getObservableList(); }
	@Override public ObservableList<Constraint> getConstraints()  		{ return constraints.getObservableList(); }
	@Override public ObservableList<Tag> getTags() 						{ return tags.getObservableList(); }
	@Override public ObservableValue<Optional<AssignmentGroup>> getGroup() { return group.getObservableValue(); }

}
