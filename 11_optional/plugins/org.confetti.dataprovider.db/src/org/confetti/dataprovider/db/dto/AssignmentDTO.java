package org.confetti.dataprovider.db.dto;

import org.confetti.core.Assignment;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;

public class AssignmentDTO implements Assignment {

    private final Subject subj;
    private final ListMutator<Teacher> teachers = new ListMutator<>();
    private final ListMutator<StudentGroup> stGroups = new ListMutator<>();

    public AssignmentDTO(Subject subj) {
        this.subj = subj;
        subj.addAssignment(this);
    }

    public void addTeacher(Teacher teacher) {
        teachers.addItem(teacher);
        teacher.addAssignment(this);
    }

    public void addStudentGroup(StudentGroup group) {
        stGroups.addItem(group);
        group.addAssignment(this);
    }

    @Override
    public Subject getSubject() {
        return subj;
    }

    @Override
    public ObservableList<Teacher> getTeachers() {
        return teachers.getObservableList();
    }

    @Override
    public ObservableList<StudentGroup> getStudentGroups() {
        return stGroups.getObservableList();
    }

    @Override
    public Room getRoom() {
        return null;
    }
}
