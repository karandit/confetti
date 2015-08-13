package org.confetti.dataprovider.xml;

import org.confetti.core.EntityVisitor;
import org.confetti.core.Room;

/**
 * @author Kárándi Tamás
 */
class RoomImpl extends EntityImpl implements Room {
	public RoomImpl(String name) { super(name); }

	@Override
	public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
		return visitor.visitRoom(this, param);
	}
}

