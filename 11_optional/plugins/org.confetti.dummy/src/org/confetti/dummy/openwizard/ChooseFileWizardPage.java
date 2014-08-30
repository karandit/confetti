package org.confetti.dummy.openwizard;

import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class ChooseFileWizardPage extends WizardPage {

	protected ChooseFileWizardPage() {
		//TODO:create a model
		super("Choose", "Choose an XML File", null);
		setPageComplete(true);
	}

	@Override
	public void createControl(Composite parent) {
		final Composite compo = new Composite(parent, SWT.NONE);
		FileFieldEditor ffe = new FileFieldEditor("file", "File", compo);
		ffe.setFileExtensions(new String[] {"*.*"});
		ffe.setPropertyChangeListener(new IPropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				setPageComplete(event.getNewValue() != null);
			}
		});
		setControl(compo);
	}

}
