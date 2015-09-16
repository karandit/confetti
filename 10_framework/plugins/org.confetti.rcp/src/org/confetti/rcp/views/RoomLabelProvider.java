package org.confetti.rcp.views;

import org.confetti.core.Room;

public class RoomLabelProvider extends EntityTableLabelProvider {
	@Override
	public String getColumnText(Object element, int columnIndex) {
		switch (columnIndex) {
			case 2: return ((Room) element).getBuilding().getValue().map(b -> b.getName().getValue()).orElse("");
			default: return super.getColumnText(element, columnIndex);
		}
	}
}
