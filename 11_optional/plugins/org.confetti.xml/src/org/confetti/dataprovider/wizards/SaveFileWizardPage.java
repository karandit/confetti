package org.confetti.dataprovider.wizards;

import static org.confetti.rcp.ConfettiPlugin.getImageDescriptor;

import java.io.File;

import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.common.io.Files;

/**
 * @author Gabor Bubla
 */
public class SaveFileWizardPage extends WizardPage {

    private NewXmlWizardModel model;

    public SaveFileWizardPage(NewXmlWizardModel model) {
        super("Choose", "FET", getImageDescriptor(ConfettiPlugin.IMG_BIG_FILE));
        this.model = model;
        setDescription("Choose a location and a name for a FET file");
        setPageComplete(false);
    }

    @Override
    public void createControl(Composite parent) {
        final Composite compo = new Composite(parent, SWT.NONE);
        final FileFieldEditor ffe = new FileFieldEditor("file", "New file", compo);
        ffe.setFileExtensions(new String[] {"*.fet"});
        //FIXME: remove for the text to be editable with a better validator later
        ffe.getTextControl(compo).setEditable(false);
        ffe.setPropertyChangeListener(new IPropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                //FIXME: button click calls this. change it to listen every change of the field's String
                if (event.getNewValue() instanceof String && event.getNewValue() != "" && StringFieldEditor.VALUE.equals(event.getProperty())) {
                    String newValue = (String) event.getNewValue();
                    File f = new File(newValue);
                    setPageComplete(!f.exists());
                    if (!f.exists()) {
                        if (Files.getFileExtension(newValue).equals("fet")) {
                            model.setFile(f);
                        } else {
                            newValue = newValue + ".fet";
                            model.setFile(new File(newValue));
                        }
                    }
                }
            }
        });
        setControl(compo);
    }

}
