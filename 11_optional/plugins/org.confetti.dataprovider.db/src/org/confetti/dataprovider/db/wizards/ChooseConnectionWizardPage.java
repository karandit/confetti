package org.confetti.dataprovider.db.wizards;

import static org.confetti.rcp.ConfettiPlugin.getImageDescriptor;

import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Gabor Bubla
 */
public class ChooseConnectionWizardPage extends WizardPage {

	private final CreateDatabaseWizardModel model;

	public ChooseConnectionWizardPage(CreateDatabaseWizardModel model) {
		super("Choose", "Database", getImageDescriptor(ConfettiPlugin.IMG_BIG_DB));
		this.model = model;
		setDescription("Choose a connection");
		setPageComplete(true);
	}

	@Override
	public void createControl(Composite parent) {
		final Composite compo = new Composite(parent, SWT.NONE);
		this.model.setConnection("");
		setControl(compo);
	}

}
