package org.confetti.dataprovider.xml;

import org.confetti.core.Assignment;
import org.confetti.core.AssignmentGroup;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;

public class AssignmentGroupImpl implements AssignmentGroup {

	private Integer id;
	private final ListMutator<Assignment> assignments = new ListMutator<>();

	public AssignmentGroupImpl(final Integer id) {
		this.id = id;
	}
	
	public Integer getId() { return id; }
	@Override public void addAssignment(Assignment assignment) 	  { assignments.addItem(assignment);}
	@Override public void removeAssignment(Assignment assignment) { assignments.removeItem(assignment); }
	@Override public ObservableList<Assignment> getAssignments()  { return assignments.getObservableList(); }

}
