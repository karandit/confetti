package org.confetti.core;

import org.confetti.observable.ObservableValue;

public interface Room extends Entity {
	ObservableValue<Integer> getCapacity();
}
