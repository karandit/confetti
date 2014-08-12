package org.confetti.rcp.commands;

import org.confetti.core.DataProvider;
import org.confetti.core.StudentGroup;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.wizards.models.NewEntityWizardModel;
import org.confetti.rcp.wizards.models.NewEntityWizardModel.EntityCreator;

public class NewStudentGroupCommand extends AbstractNewEntityHandler<StudentGroup> {

	@Override
	protected NewEntityWizardModel<StudentGroup> createModel() {
		final DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
		return new NewEntityWizardModel<StudentGroup>(getNames(dp.getStudentGroups()),
				new EntityCreator<StudentGroup>() { @Override public StudentGroup createEntity(String name) { return null; }}, 
				"New Student group", 
				"Every new line will be a new student group", 
				"The following student groups will be added");
	}
}
