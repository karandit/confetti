package org.confetti.tablix.dataprovider;

import static java.util.Optional.empty;

import java.util.Optional;

import org.confetti.core.Building;
import org.confetti.core.Room;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

/**
 * @author Kárándi Tamás
 */
class TablixRoom extends TablixEntity implements Room {
	
	private final ValueMutator<Integer> capacity = new ValueMutator<>(this, 0);
	private final ValueMutator<Optional<Building>> building = new ValueMutator<>(this, empty());
	
	public TablixRoom(String name) {
		super(name);
	}

	@Override public ObservableValue<Optional<Building>> getBuilding() { return building.getObservableValue(); }
	@Override public ObservableValue<Integer> getCapacity() { return capacity.getObservableValue(); }
}

