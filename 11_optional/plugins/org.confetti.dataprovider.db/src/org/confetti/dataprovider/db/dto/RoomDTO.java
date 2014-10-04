package org.confetti.dataprovider.db.dto;

import org.confetti.core.EntityVisitor;
import org.confetti.core.Room;

public class RoomDTO extends EntityDTO implements Room {
    public RoomDTO(String name) {
        super(name);
    }

    @Override
    public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
        return visitor.visitRoom(this, param);
    }
}