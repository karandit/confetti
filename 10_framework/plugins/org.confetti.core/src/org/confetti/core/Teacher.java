package org.confetti.core;

public interface Teacher extends Entity {
	
	@Override
	public default <R, P> R accept(NameableVisitor<R, P> visitor, P param) {
		return visitor.visitTeacher(this, param);
	}

	@Override
	public default <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
		return visitor.visitTeacher(this, param);
	}

}
