package org.confetti.rcp.views;

import org.confetti.core.DataProvider;

public class RoomsView extends AbstractEntityTableView {

	public static final String ID = "org.confetti.rcp.roomsView";

	@Override protected Object getInput(DataProvider dp) { return dp.getRooms().getList(); }

}
