package org.confetti.core;

public interface Building extends NameableVisitee {
	
	@Override
	public default <R, P> R accept(NameableVisitor<R, P> visitor, P param) {
		return visitor.visitBuilding(this, param);
	}

}
