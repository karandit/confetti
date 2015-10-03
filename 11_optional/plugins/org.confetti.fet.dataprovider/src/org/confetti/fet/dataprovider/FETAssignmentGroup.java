package org.confetti.fet.dataprovider;

import org.confetti.core.Assignment;
import org.confetti.core.AssignmentGroup;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;

public class FETAssignmentGroup implements AssignmentGroup {

	private Integer id;
	private final ListMutator<Assignment> assignments = new ListMutator<>();

	public FETAssignmentGroup(final Integer id) {
		this.id = id;
	}
	
	public Integer getId() { return id; }
	@Override public ObservableList<Assignment> getAssignments()  { return assignments.getObservableList(); }

	public void addAssignment(Assignment assignment) 	  { assignments.addItem(assignment);}
	public void removeAssignment(Assignment assignment) { assignments.removeItem(assignment); }
}
