package org.confetti.dataprovider.db.dto;

import org.confetti.core.EntityVisitor;
import org.confetti.core.Room;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

public class RoomDTO extends EntityDTO implements Room {
    
	private final ValueMutator<Integer> capacity = new ValueMutator<>(this, 0);
	
	public RoomDTO(Long id, String name) {
        super(id, name);
    }

	@Override public ObservableValue<Integer> getCapacity() { return capacity.getObservableValue(); }

	@Override
    public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
        return visitor.visitRoom(this, param);
    }
}