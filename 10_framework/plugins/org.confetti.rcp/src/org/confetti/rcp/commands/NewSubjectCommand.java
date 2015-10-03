package org.confetti.rcp.commands;

import java.util.List;

import org.confetti.core.DataPersister;
import org.confetti.core.DataProvider;
import org.confetti.core.Subject;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.nls.Messages;
import org.confetti.rcp.wizards.models.NewEntityWizardModel;
import org.confetti.rcp.wizards.models.NewEntityWizardModel.EntityCreator;

public class NewSubjectCommand extends AbstractNewEntityHandler<Subject> {

	@Override
	protected NewEntityWizardModel<Subject> createModel() {
		ConfettiPlugin plugin = ConfettiPlugin.getDefault();
		DataPersister dp = plugin.getDataPersister().get();
		DataProvider dataProvider = plugin.getDataProvider().getValue();
		return new NewEntityWizardModel<>(
				getNames(dataProvider.getSubjects().getList()),
				new EntityCreator<Subject>() { @Override public void createEntities(List<String> names) { dp.addSubjects(names); }}, 
				Messages.NewSubjectCommand_Title, 
				Messages.NewSubjectCommand_Description, 
				Messages.NewSubjectCommand_Summary,
				ConfettiPlugin.IMG_BIG_SUBJECT);
	}
}
