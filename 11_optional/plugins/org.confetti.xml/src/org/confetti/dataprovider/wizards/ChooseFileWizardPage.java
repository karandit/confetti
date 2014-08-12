package org.confetti.dataprovider.wizards;

import java.io.File;

import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class ChooseFileWizardPage extends WizardPage {

	private OpenXmlWizardModel mModel;

	protected ChooseFileWizardPage(OpenXmlWizardModel model) {
		super("Choose", "Choose an XML File", null);
		mModel = model;
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		final Composite compo = new Composite(parent, SWT.NONE);
		FileFieldEditor ffe = new FileFieldEditor("file", "File", compo);
		ffe.setFileExtensions(new String[] {"*.fet"});
		ffe.setPropertyChangeListener(new IPropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				//TODO: fix it. button click calls this
				if (StringFieldEditor.VALUE.equals(event.getProperty())) {
					setPageComplete(event.getNewValue() != null);
					mModel.setFile(new File((String) event.getNewValue()));
				}
			}
		});
		setControl(compo);
	}

}
