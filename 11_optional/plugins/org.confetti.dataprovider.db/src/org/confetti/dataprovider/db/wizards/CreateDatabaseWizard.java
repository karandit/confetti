package org.confetti.dataprovider.db.wizards;

import java.util.List;

import org.confetti.dataprovider.db.DbDataProvider;
import org.confetti.dataprovider.db.util.HibernateUtil;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.wizard.Wizard;
import org.hibernate.SessionFactory;

/**
 * @author Gabor Bubla
 */
public class CreateDatabaseWizard extends Wizard {

	private final CreateDatabaseWizardModel model;
	
	public CreateDatabaseWizard(String instituteName, String comment, List<String> days, List<String> hours) {
		model = new CreateDatabaseWizardModel(instituteName, comment, days, hours);
	}
	
	@Override
	public void addPages() {
		super.addPages();
		addPage(new ChooseConnectionWizardPage(model));
	}

	@Override
	public boolean performFinish() {
		SessionFactory sessFact = HibernateUtil.createSessionFactory(model.getConnection());
		ConfettiPlugin.getDefault().setDataProvider(new DbDataProvider(sessFact));
		return true;
	}
	
}
