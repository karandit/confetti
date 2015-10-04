package org.confetti.rcp.commands;

import org.confetti.core.DataPersister;
import org.confetti.core.DataProvider;
import org.confetti.core.Subject;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.nls.Messages;
import org.confetti.rcp.wizards.models.NewEntityWizardModel;

public class NewSubjectCommand extends AbstractNewEntityHandler<Subject> {

	@Override
	protected NewEntityWizardModel<Subject> createModel(DataProvider dataProvider, DataPersister dataPersister) {
		return new NewEntityWizardModel<>(
				dataProvider.getSubjects(),
				dataPersister::addSubjects, 
				Messages.NewSubjectCommand_Title, 
				Messages.NewSubjectCommand_Description, 
				Messages.NewSubjectCommand_Summary,
				ConfettiPlugin.IMG_BIG_SUBJECT);
	}
}
