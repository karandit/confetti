package org.confetti.fet.engine;

import static org.confetti.rcp.ConfettiPlugin.getImageDescriptor;

import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Gabor Bubla
 */
public class FETEngineWizardPage extends WizardPage {

	protected FETEngineWizardPage(String pageName) {
		super("Generate", "FET", getImageDescriptor(ConfettiPlugin.IMG_BIG_ENGINE));
		setDescription("description");
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("Generating blabla");
		setControl(label);
	}

}
