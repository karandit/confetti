package org.confetti.dataprovider.db.wizards;

import java.util.List;

import org.confetti.dataprovider.db.DbDataProvider;
import org.confetti.dataprovider.db.entities.InstituteDb;
import org.confetti.dataprovider.db.util.HibernateUtil;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
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
        addPage(new ChooseConnectionWizardPage(model));
    }

    @Override
    public boolean performFinish() {
        SessionFactory sessFact = HibernateUtil.createSessionFactory(model.getConnection());
        Session session = sessFact.openSession();
        try {
            Criteria criteria = session.createCriteria(InstituteDb.class);
            List<InstituteDb> institutes = (List<InstituteDb>) criteria.list();
            if (institutes.isEmpty()) {
                MessageDialog.openError(getShell(), "Error", "No Institute found in the database.");
                return true;
            }
            InstituteDb inst = institutes.get(0);
            DbDataProvider dp = new DbDataProvider(sessFact, inst);
            ConfettiPlugin.getDefault().setDataProvider(dp);
            return true;
        } finally {
            session.close();
        }
    }

}
