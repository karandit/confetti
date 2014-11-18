package org.confetti.dataprovider.db.wizards;

import java.util.List;

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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Gabor Bubla
 */
public class OpenDatabaseWizard extends Wizard {

    private OpenDatabaseWizardModel model = new OpenDatabaseWizardModel();
    
    @Override
    public void addPages() {
        setWindowTitle("Open");
        addPage(new ChooseConnectionWizardPage(model));
    }

    @Override
    public boolean performFinish() {
        //TODO: DRY with CreateDatabaseWizard#performFinish
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
            Criteria criteria = session.createCriteria(InstituteDb.class);
            List<InstituteDb> institutes = (List<InstituteDb>) criteria.list();
            if (institutes.isEmpty()) {
                MessageDialog.openError(getShell(), "Error", "No Institute found in the database.");
                return true;
            }
            InstituteDb inst = institutes.get(0);
            String info = selConn.getSecond() + " : " + selConn.getFirst();
            DbDataProvider dp = new DbDataProvider(sessFact, inst, info);
            ConfettiPlugin.getDefault().setDataProvider(dp, dp);
            return true;
        } finally {
            session.close();
        }
    }

}
