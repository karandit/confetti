package org.confetti.rcp.commands;

import java.util.List;

import org.confetti.core.DataProvider;
import org.confetti.core.Subject;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.wizards.models.NewEntityWizardModel;
import org.confetti.rcp.wizards.models.NewEntityWizardModel.EntityCreator;

public class NewSubjectCommand extends AbstractNewEntityHandler<Subject> {

	@Override
	protected NewEntityWizardModel<Subject> createModel() {
		final DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
		return new NewEntityWizardModel<Subject>(
				getNames(dp.getSubjects().getList()),
				new EntityCreator<Subject>() { @Override public void createEntities(List<String> names) { dp.addSubjects(names); }}, 
				"New Subject", 
				"Every new line will be a new subject", 
				"The following subjects will be added",
				ConfettiPlugin.IMG_BIG_SUBJECT);
	}
}
