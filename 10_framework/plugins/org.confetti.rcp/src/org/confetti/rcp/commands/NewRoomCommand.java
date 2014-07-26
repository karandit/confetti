package org.confetti.rcp.commands;

import org.confetti.core.DataProvider;
import org.confetti.core.Room;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.wizards.NewEntityWizardModel;
import org.confetti.rcp.wizards.NewEntityWizardModel.EntityCreator;

public class NewRoomCommand extends AbstractNewEntityHandler<Room> {

	@Override
	protected NewEntityWizardModel<Room> createModel() {
		final DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
		return new NewEntityWizardModel<Room>(
				getNames(dp.getRooms()), 
				new EntityCreator<Room>() { @Override public Room createEntity(String name) { return dp.addRoom(name); }}, 
				"New Room", 
				"Every new line will be a new room", 
				"The following rooms will be added");
	}
}
