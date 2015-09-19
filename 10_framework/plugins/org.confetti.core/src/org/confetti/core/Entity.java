package org.confetti.core;

public interface Entity extends NameableVisitee, Assignable, Constraintable {
	<R, P> R accept(EntityVisitor<R, P> visitor, P param);
}
