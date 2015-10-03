package org.confetti.rcp.commands;

import java.util.List;

import org.confetti.core.DataPersister;
import org.confetti.core.DataProvider;
import org.confetti.core.Teacher;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.wizards.models.NewEntityWizardModel;
import org.confetti.rcp.wizards.models.NewEntityWizardModel.EntityCreator;
import org.confetti.rcp.nls.Messages;

public class NewTeacherCommand extends AbstractNewEntityHandler<Teacher> {

	@Override
	protected NewEntityWizardModel<Teacher> createModel() {
		ConfettiPlugin plugin = ConfettiPlugin.getDefault();
		DataPersister dp = plugin.getDataPersister().get();
		DataProvider dataProvider = plugin.getDataProvider().getValue();
		return new NewEntityWizardModel<>(
				getNames(dataProvider.getTeachers().getList()),
				new EntityCreator<Teacher>() { @Override public void createEntities(List<String> names) { dp.addTeachers(names); }}, 
				Messages.NewTeacherCommand_Title, 
				Messages.NewTeacherCommand_Description, 
				Messages.NewTeacherCommand_Summary,
				ConfettiPlugin.IMG_BIG_TEACHER);
	}

}
