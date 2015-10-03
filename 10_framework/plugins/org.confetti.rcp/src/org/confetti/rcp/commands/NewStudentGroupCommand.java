package org.confetti.rcp.commands;

import java.util.List;

import org.confetti.core.DataPersister;
import org.confetti.core.DataProvider;
import org.confetti.core.StudentGroup;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.nls.Messages;
import org.confetti.rcp.wizards.models.NewEntityWizardModel;
import org.confetti.rcp.wizards.models.NewEntityWizardModel.EntityCreator;

public class NewStudentGroupCommand extends AbstractNewEntityHandler<StudentGroup> {

	@Override
	protected NewEntityWizardModel<StudentGroup> createModel() {
		ConfettiPlugin plugin = ConfettiPlugin.getDefault();
		DataPersister dp = plugin.getDataPersister().get();
		DataProvider dataProvider = plugin.getDataProvider().getValue();
		return new NewEntityWizardModel<>(getNames(dataProvider.getStudentGroups().getList()),
				//FIXME use the parent parameter of DataProvider.addStudentGroup
		        new EntityCreator<StudentGroup>() { @Override public void createEntities(List<String> names) { dp.addStudentGroups(null, names); }}, 
				Messages.NewStudentGroupCommand_Title, 
				Messages.NewStudentGroupCommand_Description, 
				Messages.NewStudentGroupCommand_Summary,
				ConfettiPlugin.IMG_BIG_STUDENTGROUP);
	}
}
