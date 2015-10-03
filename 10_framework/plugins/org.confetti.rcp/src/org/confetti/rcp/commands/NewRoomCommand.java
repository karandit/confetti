package org.confetti.rcp.commands;

import java.util.List;

import org.confetti.core.DataPersister;
import org.confetti.core.DataProvider;
import org.confetti.core.Room;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.nls.Messages;
import org.confetti.rcp.wizards.models.NewEntityWizardModel;
import org.confetti.rcp.wizards.models.NewEntityWizardModel.EntityCreator;

public class NewRoomCommand extends AbstractNewEntityHandler<Room> {

	@Override
	protected NewEntityWizardModel<Room> createModel() {
		ConfettiPlugin plugin = ConfettiPlugin.getDefault();
		DataPersister dp = plugin.getDataPersister().get();
		DataProvider dataProvider = plugin.getDataProvider().getValue();
		return new NewEntityWizardModel<>(
				getNames(dataProvider.getRooms().getList()), 
				new EntityCreator<Room>() { @Override public void createEntities(List<String> names) { dp.addRooms(names); }}, 
				Messages.NewRoomCommand_Title, 
				Messages.NewRoomCommand_Description, 
				Messages.NewRoomCommand_Summary,
				ConfettiPlugin.IMG_BIG_ROOM);
	}
}
