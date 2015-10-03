package org.confetti.fet.dataprovider;

import org.confetti.core.Assignment;
import org.confetti.core.Constraint;
import org.confetti.core.Entity;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

/**
 * @author K�r�ndi Tam�s
 */
abstract class FETEntity implements Entity {

	private final ValueMutator<String> name;
	private final ListMutator<Assignment> assignments = new ListMutator<>();
	private final ListMutator<Constraint> constraints = new ListMutator<>();
	
	public FETEntity(String name) {
		this.name = new ValueMutator<>(this, name);
	}
	
	@Override public ObservableValue<String> getName() 			  { return name.getObservableValue(); }
	@Override public void addAssignment(Assignment assignment) 	  { assignments.addItem(assignment);}
	@Override public void removeAssignment(Assignment assignment) { assignments.removeItem(assignment); }
	@Override public ObservableList<Assignment> getAssignments()  { return assignments.getObservableList(); }
	@Override public ObservableList<Constraint> getConstraints()  { return constraints.getObservableList(); }

	public ValueMutator<String> getNameMutator() { return name; }
	public void addConstraint(Constraint constraint) { constraints.addItem(constraint);}

}
