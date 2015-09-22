package org.confetti.core;

public interface Subject extends Entity {
	int getColor();

	@Override
	public default <R, P> R accept(NameableVisitor<R, P> visitor, P param) {
		return visitor.visitSubject(this, param);
	}

	@Override
	public default <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
		return visitor.visitSubject(this, param);
	}

}
