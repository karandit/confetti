package org.confetti.rcp.commands;

import org.confetti.core.DataPersister;
import org.confetti.core.DataProvider;
import org.confetti.core.Room;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.nls.Messages;
import org.confetti.rcp.wizards.models.NewEntityWizardModel;

public class NewRoomCommand extends AbstractNewEntityHandler<Room> {

	@Override
	protected NewEntityWizardModel<Room> createModel(DataProvider dataProvider, DataPersister dataPersister) {
		return new NewEntityWizardModel<>(
				dataProvider.getRooms(), 
				dataPersister::addRooms, 
				Messages.NewRoomCommand_Title, 
				Messages.NewRoomCommand_Description, 
				Messages.NewRoomCommand_Summary,
				ConfettiPlugin.IMG_BIG_ROOM);
	}
}
