package org.confetti.core;

import org.confetti.observable.ObservableList;

public interface Tag extends NameableVisitee {
	ObservableList<Assignment> getAssignments();
	
	@Override
	public default <R, P> R accept(NameableVisitor<R, P> visitor, P param) {
		return visitor.visitTag(this, param);
	}

}
