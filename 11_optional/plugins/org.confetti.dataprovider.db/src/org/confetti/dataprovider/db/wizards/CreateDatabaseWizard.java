package org.confetti.dataprovider.db.wizards;

import java.util.List;

import org.confetti.dataprovider.db.DbDataProvider;
import org.confetti.dataprovider.db.entities.InstituteDb;
import org.confetti.dataprovider.db.util.HibernateUtil;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.wizard.Wizard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
		DbDataProvider dp = new DbDataProvider(sessFact);
		InstituteDb instDb = new InstituteDb(model.getInstituteName(), model.getComment(), model.getDays(), model.getHours());
		Session session = sessFact.openSession();
		try {
		    Transaction tx = session.beginTransaction();
		    try {
		        session.persist(instDb);
		        tx.commit();
            } catch (Exception e) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        ConfettiPlugin.getDefault().setDataProvider(dp);
		return true;
	}
	
}
