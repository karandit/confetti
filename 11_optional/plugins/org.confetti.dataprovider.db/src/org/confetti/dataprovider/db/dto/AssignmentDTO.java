package org.confetti.dataprovider.db.dto;

import org.confetti.core.Assignment;
import org.confetti.core.Constraint;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Tag;
import org.confetti.core.Teacher;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

public class AssignmentDTO implements Assignment {

    private final Long id;
    private final ValueMutator<Integer> duration = new ValueMutator<>(this, 1);
    private final Subject subj;
    private final ListMutator<Teacher> teachers = new ListMutator<>();
    private final ListMutator<StudentGroup> stGroups = new ListMutator<>();
	private final ListMutator<Constraint> constraints = new ListMutator<>();
	private final ListMutator<Tag> tags = new ListMutator<>();

    public AssignmentDTO(Long id, Subject subj) {
        this.id = id;
        this.subj = subj;
        subj.addAssignment(this);
    }

    public Long getId() {
        return id;
    }
    
    public void addTeacher(Teacher teacher) {
        teachers.addItem(teacher);
        teacher.addAssignment(this);
    }

    public void addStudentGroup(StudentGroup group) {
        stGroups.addItem(group);
        group.addAssignment(this);
    }

    @Override public ObservableValue<Integer> getDuration() { return duration.getObservableValue(); }
    @Override public Subject getSubject() { return subj; }
    @Override public ObservableList<Teacher> getTeachers() { return teachers.getObservableList(); }
    @Override public ObservableList<StudentGroup> getStudentGroups() { return stGroups.getObservableList(); }
	@Override public ObservableList<Constraint> getConstraints()  { return constraints.getObservableList(); }
	@Override public ObservableList<Tag> getTags() { return tags.getObservableList(); }

}
