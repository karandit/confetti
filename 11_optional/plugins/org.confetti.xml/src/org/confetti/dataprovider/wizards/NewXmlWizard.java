package org.confetti.dataprovider.wizards;

import java.io.IOException;

import org.confetti.dataprovider.xml.XmlDataProvider;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.xml.FAOException;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Gabor Bubla
 */
public class NewXmlWizard extends Wizard {

    private NewXmlWizardModel model;

    public NewXmlWizard(NewXmlWizardModel model) {
        this.model = model;
    }
    
    @Override
    public void addPages() {
        setWindowTitle("New");
        addPage(new SaveFileWizardPage(model));
    }

    @Override
    public boolean performFinish() {
        try {
            model.getFile().createNewFile();
            ConfettiPlugin.getDefault().setDataProvider(new XmlDataProvider(model.getFile()));
        } catch (FAOException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
