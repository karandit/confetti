package org.confetti.dataprovider.db.wizards;

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
	
	public CreateDatabaseWizard(CreateDatabaseWizardModel model) {
        this.model = model;
    }

    @Override
	public void addPages() {
		addPage(new ChooseConnectionWizardPage(model));
	}

	@Override
	public boolean performFinish() {
		SessionFactory sessFact = HibernateUtil.createSessionFactory(model.getConnection());
		Session session = sessFact.openSession();
		try {
		    Transaction tx = session.beginTransaction();
		    try {
		        InstituteDb instDb = new InstituteDb(model.getDataProvider());
		        session.persist(instDb);
		        tx.commit();
		        ConfettiPlugin.getDefault().setDataProvider(new DbDataProvider(sessFact, instDb));
		        return true;
            } catch (Exception e) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
		return false;
	}
	
}
