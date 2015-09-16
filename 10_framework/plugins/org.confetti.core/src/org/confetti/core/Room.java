package org.confetti.core;

import java.util.Optional;

import org.confetti.observable.ObservableValue;

public interface Room extends Entity {
	ObservableValue<Integer> getCapacity();
	ObservableValue<Optional<Building>> getBuilding();
}
