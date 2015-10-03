package org.confetti.dataprovider.db.dto;

import org.confetti.core.Assignable;
import org.confetti.core.Assignment;
import org.confetti.core.Constraint;
import org.confetti.core.Entity;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

public abstract class EntityDTO implements Entity, Assignable {

    private final Long id;

    private final ValueMutator<String> name;
    private final ListMutator<Assignment> assignments = new ListMutator<>();
	private final ListMutator<Constraint> constraints = new ListMutator<>();

    public EntityDTO(final Long id, String name) {
        this.id = id;
        this.name = new ValueMutator<>(this, name);
    }
    
    public Long getId() { return id; }
    
    @Override public ObservableValue<String> getName() { return name.getObservableValue(); }
    @Override public ObservableList<Assignment> getAssignments() { return assignments.getObservableList(); }
	@Override public ObservableList<Constraint> getConstraints()  { return constraints.getObservableList(); }

    public ValueMutator<String> getNameMutator() { return name; }
    public void addAssignment(Assignment assignment) { assignments.addItem(assignment); }
    public void removeAssignment(Assignment assignment) { assignments.removeItem(assignment); }

}