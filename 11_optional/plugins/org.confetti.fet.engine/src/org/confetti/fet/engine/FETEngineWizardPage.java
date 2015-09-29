package org.confetti.fet.engine;

import static org.confetti.rcp.ConfettiPlugin.getImageDescriptor;

import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Gabor Bubla
 */
public class FETEngineWizardPage extends WizardPage {
	
	private StyledText console;
	private Button verbose;
	
	FETEngineWizardPage(String pageName) {
		super("Generate", "FET", getImageDescriptor(ConfettiPlugin.IMG_BIG_ENGINE));
		setDescription("Generating timetables based on assignments");
		setPageComplete(true);
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		console = new StyledText(composite, SWT.READ_ONLY | SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
		console.setLayoutData(new GridData(GridData.FILL_BOTH));
		console.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		console.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_GREEN));

		verbose = new Button(composite, SWT.CHECK);
		verbose.setText("Verbose");
		
		setControl(composite);
	}
	
	
	boolean isVerbose() {
		return verbose.getSelection();
	}
	
	void setReadOnly() {
		verbose.setEnabled(false);
	}
	
	void print(final String text) {
		console.setText(console.getText() + text + "\n");
		console.setTopIndex(console.getLineCount() - 1);
	}
}
