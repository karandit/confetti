package org.confetti.rcp.commands;

import org.confetti.core.DataProvider;
import org.confetti.core.Subject;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.wizards.NewEntityWizardModel;
import org.confetti.rcp.wizards.NewEntityWizardModel.EntityCreator;

public class NewSubjectCommand extends AbstractNewEntityHandler<Subject> {

	@Override
	protected NewEntityWizardModel<Subject> createModel() {
		final DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
		return new NewEntityWizardModel<Subject>(
				getNames(dp.getSubjects()),
				new EntityCreator<Subject>() { @Override public Subject createEntity(String name) { return dp.addSubject(name); }}, 
				"New Subject", 
				"Every new line will be a new subject", 
				"The following subjects will be added");
	}
}
