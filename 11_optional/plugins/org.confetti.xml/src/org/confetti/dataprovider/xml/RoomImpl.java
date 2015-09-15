package org.confetti.dataprovider.xml;

import org.confetti.core.EntityVisitor;
import org.confetti.core.Room;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

/**
 * @author Kárándi Tamás
 */
class RoomImpl extends EntityImpl implements Room {
	
	private final ValueMutator<Integer> capacity;
	
	public RoomImpl(String name, int capacity) {
		super(name);
		this.capacity = new ValueMutator<>(this, capacity);
	}

	@Override
	public ObservableValue<Integer> getCapacity() {
		return capacity.getObservableValue();
	}

	@Override
	public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
		return visitor.visitRoom(this, param);
	}
}

