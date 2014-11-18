package org.confetti.dataprovider.db.wizards;

import org.confetti.dataprovider.db.DbConnectionDescriptor;
import org.confetti.dataprovider.db.DbConnectionFactory;
import org.confetti.dataprovider.db.DbDataProvider;
import org.confetti.dataprovider.db.entities.InstituteDb;
import org.confetti.dataprovider.db.util.HibernateUtil;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.extensions.ConnectionDescr;
import org.confetti.rcp.extensions.ConnectionFactory;
import org.confetti.rcp.extensions.ConnectionRegistry;
import org.confetti.util.Tuple;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.Wizard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * @author Gabor Bubla
 */
public class NewDatabaseWizard extends Wizard {

	private final NewDatabaseWizardModel model;
	
	public NewDatabaseWizard(NewDatabaseWizardModel model) {
        this.model = model;
    }

    @Override
	public void addPages() {
        setWindowTitle("New");
		addPage(new ChooseConnectionWizardPage(model));
	}

	@Override
	public boolean performFinish() {
		Tuple<String, String> selConn = model.getSelectedConnection();
		ConnectionDescr connDescr = ConnectionRegistry.INSTANCE.getConnectionByType(selConn.getSecond());
		ConnectionFactory connFact = connDescr.getConnectionFactory();
		//If this happens, means the category of "DB" doesn't match with the ConnectionFactory
		if (!(connFact instanceof DbConnectionFactory)) {
		    MessageDialog.openError(getShell(), "Error", "The selected connection is not a database connection.");
		    return false;
		}
		
		DbConnectionFactory dbConnFact = (DbConnectionFactory) connFact;
		IPreferenceStore prefStore = ConfettiPlugin.getDefault().getPreferenceStore();
        DbConnectionDescriptor dbConnDescr = dbConnFact.createConnectionDescriptor(selConn.getFirst(), prefStore);
		
        SessionFactory sessFact = HibernateUtil.createSessionFactory(dbConnDescr);
		Session session = sessFact.openSession();
		try {
		    Transaction tx = session.beginTransaction();
		    try {
		        InstituteDb instDb = new InstituteDb(model.getDataProvider());
		        session.persist(instDb);
		        tx.commit();
		        String info = selConn.getSecond() + " : " + selConn.getFirst();
		        DbDataProvider dp = new DbDataProvider(sessFact, instDb, info);
                ConfettiPlugin.getDefault().setDataProvider(dp, dp);
		        return true;
            } catch (Exception e) {
                tx.rollback();
                MessageDialog.openError(getShell(), "Error", e.getMessage());
                throw e;
            }
        } finally {
            session.close();
        }
	}
	
}
