package org.confetti.dataprovider.xml;

import java.util.Optional;

import org.confetti.core.Building;
import org.confetti.core.EntityVisitor;
import org.confetti.core.Room;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

/**
 * @author Kárándi Tamás
 */
class RoomImpl extends EntityImpl implements Room {
	
	private final ValueMutator<Integer> capacity;
	private final ValueMutator<Optional<Building>> building;
	
	public RoomImpl(String name, int capacity, Optional<Building> building) {
		super(name);
		this.capacity = new ValueMutator<>(this, capacity);
		this.building = new ValueMutator<>(this, building);
	}

	@Override public ObservableValue<Optional<Building>> getBuilding() { return building.getObservableValue(); }
	@Override public ObservableValue<Integer> getCapacity() { return capacity.getObservableValue(); }
	@Override public <R, P> R accept(EntityVisitor<R, P> visitor, P param) { return visitor.visitRoom(this, param); }
}

