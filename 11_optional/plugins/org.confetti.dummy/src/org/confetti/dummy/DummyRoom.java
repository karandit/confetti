package org.confetti.dummy;

import static java.util.Optional.empty;

import java.util.Optional;

import org.confetti.core.Building;
import org.confetti.core.Room;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

public class DummyRoom extends DummyEntity implements Room {

	private final ValueMutator<Integer> capacity = new ValueMutator<>(this, 0);
	private final ValueMutator<Optional<Building>> building = new ValueMutator<>(this, empty());
	
	public DummyRoom(String name) {
		super(name);
	}

	@Override public ObservableValue<Integer> getCapacity() { return capacity.getObservableValue(); }
	@Override public ObservableValue<Optional<Building>> getBuilding() { return building.getObservableValue(); }

}
