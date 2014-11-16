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

public class ChooseFileWizardPage extends WizardPage {

	private OpenXmlWizardModel mModel;

	protected ChooseFileWizardPage(OpenXmlWizardModel model) {
		super("Choose", "FET", getImageDescriptor(ConfettiPlugin.IMG_BIG_FILE));
		mModel = model;
		setDescription("Choose a FET file");
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		final Composite compo = new Composite(parent, SWT.NONE);
		FileFieldEditor ffe = new FileFieldEditor("file", "File", compo);
		ffe.setFileExtensions(new String[] {"*.fet"});
		//FIXME: remove for the text to be editable with a better validator later
		ffe.getTextControl(compo).setEditable(false);
		ffe.setPropertyChangeListener(new IPropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				//FIXME: button click calls this. change it to listen every change of the field's String
				if (event.getNewValue() instanceof String && StringFieldEditor.VALUE.equals(event.getProperty())) {
				    String newValue = (String) event.getNewValue();
				    if (Files.getFileExtension(newValue).equals("fet")) {
				        File f = new File(newValue);
				        setPageComplete(f.exists());
				        if (f.exists()) {
				            mModel.setFile(f);
				        }
                    } else {
                        setPageComplete(false);
                    }
				}
			}
		});
		setControl(compo);
	}

}
