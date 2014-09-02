package org.confetti.rcp.commands;

import org.confetti.core.DataProvider;
import org.confetti.core.Teacher;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.wizards.models.NewEntityWizardModel;
import org.confetti.rcp.wizards.models.NewEntityWizardModel.EntityCreator;

public class NewTeacherCommand extends AbstractNewEntityHandler<Teacher> {

	@Override
	protected NewEntityWizardModel<Teacher> createModel() {
		final DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
		return new NewEntityWizardModel<Teacher>(getNames(dp.getTeachers().getList()),
				new EntityCreator<Teacher>() { @Override public Teacher createEntity(String name) { return dp.addTeacher(name); }}, 
				"New Teacher", 
				"Every new line will be a new teacher", 
				"The following teachers will be added",
				ConfettiPlugin.IMG_BIG_TEACHER);
	}

}
