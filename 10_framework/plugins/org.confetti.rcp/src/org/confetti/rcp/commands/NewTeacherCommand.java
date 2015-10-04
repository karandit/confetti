package org.confetti.rcp.commands;

import org.confetti.core.DataPersister;
import org.confetti.core.DataProvider;
import org.confetti.core.Teacher;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.nls.Messages;
import org.confetti.rcp.wizards.models.NewEntityWizardModel;

public class NewTeacherCommand extends AbstractNewEntityHandler<Teacher> {

	@Override
	protected NewEntityWizardModel<Teacher> createModel(DataProvider dataProvider, DataPersister dataPersister) {
		return new NewEntityWizardModel<>(
				dataProvider.getTeachers(),
				dataPersister::addTeachers, 
				Messages.NewTeacherCommand_Title, 
				Messages.NewTeacherCommand_Description, 
				Messages.NewTeacherCommand_Summary,
				ConfettiPlugin.IMG_BIG_TEACHER);
	}

}
