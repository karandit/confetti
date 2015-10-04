package org.confetti.rcp.commands;

import org.confetti.core.DataPersister;
import org.confetti.core.DataProvider;
import org.confetti.core.Tag;
import org.confetti.rcp.nls.Messages;
import org.confetti.rcp.wizards.models.NewEntityWizardModel;

public class NewTagCommand extends AbstractNewEntityHandler<Tag> {

	@Override
	protected NewEntityWizardModel<Tag> createModel(DataProvider dataProvider, DataPersister dataPersister) {
		return new NewEntityWizardModel<>(
				dataProvider.getTags(),
				dataPersister::addTags, 
				Messages.NewTagCommand_Title, 
				Messages.NewTagCommand_Description, 
				Messages.NewTagCommand_Summary,
				null); //TODO: create an image for tags
	}

}
