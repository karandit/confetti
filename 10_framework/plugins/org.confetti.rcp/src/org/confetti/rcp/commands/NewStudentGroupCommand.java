package org.confetti.rcp.commands;

import org.confetti.core.DataPersister;
import org.confetti.core.DataProvider;
import org.confetti.core.StudentGroup;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.nls.Messages;
import org.confetti.rcp.wizards.models.NewEntityWizardModel;

public class NewStudentGroupCommand extends AbstractNewEntityHandler<StudentGroup> {

	@Override
	protected NewEntityWizardModel<StudentGroup> createModel(DataProvider dataProvider, DataPersister dataPersister) {
		return new NewEntityWizardModel<>(
				dataProvider.getStudentGroups(),
				//FIXME use the parent parameter of DataProvider.addStudentGroup
		        names-> dataPersister.addStudentGroups(null, names), 
				Messages.NewStudentGroupCommand_Title, 
				Messages.NewStudentGroupCommand_Description, 
				Messages.NewStudentGroupCommand_Summary,
				ConfettiPlugin.IMG_BIG_STUDENTGROUP);
	}
}
