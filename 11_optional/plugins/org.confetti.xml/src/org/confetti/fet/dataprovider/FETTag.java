package org.confetti.fet.dataprovider;

import org.confetti.core.Assignment;
import org.confetti.core.Tag;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

public class FETTag implements Tag {

	private final ValueMutator<String> name;
	private final ListMutator<Assignment> assignments = new ListMutator<>();

	public FETTag(String name) {
		this.name = new ValueMutator<>(this, name);
	}
	
	@Override public ObservableValue<String> getName() { return name.getObservableValue(); }
	@Override public ObservableList<Assignment> getAssignments()  { return assignments.getObservableList(); }
	public void addAssignment(Assignment assignment) 	  { assignments.addItem(assignment);}

}
