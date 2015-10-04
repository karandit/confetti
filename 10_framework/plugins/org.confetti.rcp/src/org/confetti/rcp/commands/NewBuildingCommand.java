package org.confetti.rcp.commands;

import org.confetti.core.Building;
import org.confetti.core.DataPersister;
import org.confetti.core.DataProvider;
import org.confetti.rcp.nls.Messages;
import org.confetti.rcp.wizards.models.NewEntityWizardModel;

public class NewBuildingCommand extends AbstractNewEntityHandler<Building> {

	@Override
	protected NewEntityWizardModel<Building> createModel(DataProvider dataProvider, DataPersister dataPersister) {
		return new NewEntityWizardModel<>(
				dataProvider.getBuildings(), 
				dataPersister::addBuildings, 
				Messages.NewBuildingCommand_Title, 
				Messages.NewBuildingCommand_Description, 
				Messages.NewBuildingCommand_Summary,
				null); //TODO: create image for building
	}

}
