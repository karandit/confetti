package org.confetti.rcp.commands;

import java.util.List;

import org.confetti.core.DataPersister;
import org.confetti.core.DataProvider;
import org.confetti.core.Tag;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.nls.Messages;
import org.confetti.rcp.wizards.models.NewEntityWizardModel;
import org.confetti.rcp.wizards.models.NewEntityWizardModel.EntityCreator;

public class NewTagCommand extends AbstractNewEntityHandler<Tag> {

	@Override
	protected NewEntityWizardModel<Tag> createModel() {
		ConfettiPlugin plugin = ConfettiPlugin.getDefault();
		DataPersister dp = plugin.getDataPersister().get();
		DataProvider dataProvider = plugin.getDataProvider().getValue();
		return new NewEntityWizardModel<>(
				getNames(dataProvider.getTags().getList()),
				new EntityCreator<Tag>() { @Override public void createEntities(List<String> names) { dp.addTags(names); }}, 
				Messages.NewTagCommand_Title, 
				Messages.NewTagCommand_Description, 
				Messages.NewTagCommand_Summary,
				null); //TODO: create an image for tags
	}

}
