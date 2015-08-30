package org.confetti.rcp.commands;

import java.util.List;

import org.confetti.core.DataProvider;
import org.confetti.core.StudentGroup;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.wizards.models.NewEntityWizardModel;
import org.confetti.rcp.wizards.models.NewEntityWizardModel.EntityCreator;

public class NewStudentGroupCommand extends AbstractNewEntityHandler<StudentGroup> {

	@Override
	protected NewEntityWizardModel<StudentGroup> createModel() {
		final DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
		return new NewEntityWizardModel<>(getNames(dp.getStudentGroups().getList()),
				//FIXME use the parent parameter of DataProvider.addStudentGroup
		        new EntityCreator<StudentGroup>() { @Override public void createEntities(List<String> names) { dp.addStudentGroups(null, names); }}, 
				"New Student group", 
				"Every new line will be a new student group", 
				"The following student groups will be added",
				ConfettiPlugin.IMG_BIG_STUDENTGROUP);
	}
}
