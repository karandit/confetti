package org.confetti.rcp.views;

import org.confetti.core.DataProvider;
import org.confetti.core.Room;
import org.confetti.observable.ObservableList;

public class RoomsView extends AbstractEntityTableView<Room> {

	public static final String ID = "org.confetti.rcp.roomsView";

	@Override protected ObservableList<Room> getObservableList(final DataProvider dp) { return dp.getRooms(); }

}
