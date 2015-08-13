package org.confetti.dummy;

import org.confetti.core.EntityVisitor;
import org.confetti.core.Room;

public class DummyRoom extends DummyEntity implements Room {

	public DummyRoom(String name) {
		super(name);
	}
	
	@Override
	public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
		return visitor.visitRoom(this, param);
	}
	
}
