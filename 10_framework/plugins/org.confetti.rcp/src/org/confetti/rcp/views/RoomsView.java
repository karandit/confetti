package org.confetti.rcp.views;

import org.confetti.core.DataProvider;
import org.confetti.core.Room;
import org.confetti.observable.ObservableList;
import org.confetti.rcp.nls.Messages;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Table;

public class RoomsView extends AbstractEntityTableView<Room> {

	public static final String ID = "org.confetti.rcp.roomsView"; //$NON-NLS-1$

	@Override protected ObservableList<Room> getObservableList(final DataProvider dp) { return dp.getRooms(); }

	@Override protected LabelProvider getLabelProvider() { return new RoomLabelProvider(); }
	
	@Override
	protected void createColumns(Table table) {
		super.createColumns(table);
		createColumn(table, Messages.RoomsView_Column_Building, 170);
	}
}
