package org.confetti.dataprovider.db.dto;

import org.confetti.core.EntityVisitor;
import org.confetti.core.StudentGroup;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

public class StudentGroupDTO extends EntityDTO implements StudentGroup {

	private final ValueMutator<Integer> nrOfStudents = new ValueMutator<>(this, 0);
	private final ListMutator<StudentGroup> children = new ListMutator<>();

    public StudentGroupDTO(Long id, String name) {
        super(id, name);
    }

    public void addChild(StudentGroup child) {
        children.addItem(child);
    }

    @Override
	public ObservableValue<Integer> getNrOfStudents() {
		return nrOfStudents.getObservableValue();
	}

    @Override
    public ObservableList<StudentGroup> getChildren() {
        return children.getObservableList();
    }

    @Override
    public StudentGroup getParent() {
        return null;
    }

    @Override
    public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
        return visitor.visitStudentGroup(this, param);
    }
}
