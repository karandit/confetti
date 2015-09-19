package org.confetti.core;

public interface Hour extends NameableVisitee {

	@Override
	public default <R, P> R accept(NameableVisitor<R, P> visitor, P param) {
		return visitor.visitHour(this, param);
	}

}
