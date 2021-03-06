package org.confetti.fet.dataprovider;

import java.util.Optional;

import org.confetti.core.Building;
import org.confetti.core.Room;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

/**
 * @author K�r�ndi Tam�s
 */
class FETRoom extends FETEntity implements Room {
	
	private final ValueMutator<Integer> capacity;
	private final ValueMutator<Optional<Building>> building;
	
	public FETRoom(String name, int capacity, Optional<Building> building) {
		super(name);
		this.capacity = new ValueMutator<>(this, capacity);
		this.building = new ValueMutator<>(this, building);
	}

	@Override public ObservableValue<Optional<Building>> getBuilding() { return building.getObservableValue(); }
	@Override public ObservableValue<Integer> getCapacity() { return capacity.getObservableValue(); }
}

