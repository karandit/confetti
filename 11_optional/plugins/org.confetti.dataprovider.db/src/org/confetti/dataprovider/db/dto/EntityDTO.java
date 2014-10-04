package org.confetti.dataprovider.db.dto;

import org.confetti.core.Assignable;
import org.confetti.core.Assignment;
import org.confetti.core.Entity;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

public abstract class EntityDTO implements Entity, Assignable {

    private final ValueMutator<String> name;
    private final ListMutator<Assignment> assignments = new ListMutator<>();

    public EntityDTO(String name) {
        this.name = new ValueMutator<>(this, name);
    }

    @Override
    public ObservableValue<String> getName() {
        return name.getObservableValue();
    }

    @Override
    public void addAssignment(Assignment assignment) {
        assignments.addItem(assignment);
    }

    @Override
    public ObservableList<Assignment> getAssignments() {
        return assignments.getObservableList();
    }

    public ValueMutator<String> getNameMutator() {
        return name;
    }

}