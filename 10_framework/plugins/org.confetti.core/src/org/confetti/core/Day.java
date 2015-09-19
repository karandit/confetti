package org.confetti.core;

public interface Day extends NameableVisitee {

	@Override
	public default <R, P> R accept(NameableVisitor<R, P> visitor, P param) {
		return visitor.visitDay(this, param);
	}

}
