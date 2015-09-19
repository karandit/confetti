package org.confetti.core;

public interface NameableVisitee extends Nameable {
	<R, P> R accept(NameableVisitor<R, P> visitor, P param);
}
