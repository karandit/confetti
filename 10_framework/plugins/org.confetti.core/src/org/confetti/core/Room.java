package org.confetti.core;

import java.util.Optional;

import org.confetti.observable.ObservableValue;

public interface Room extends Entity {
	ObservableValue<Integer> getCapacity();
	ObservableValue<Optional<Building>> getBuilding();

	@Override
	public default <R, P> R accept(NameableVisitor<R, P> visitor, P param) {
		return visitor.visitRoom(this, param);
	}

	@Override 
	public default <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
		return visitor.visitRoom(this, param);
	}

}
