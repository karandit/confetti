package org.confetti.dummy.openwizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;

public class ChooseFileWizardPage extends WizardPage {

	protected ChooseFileWizardPage() {
		super("Choose", "Choose an XML File", null);
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		final Composite compo = new Composite(parent, SWT.BORDER);
		compo.setLayout(new GridLayout());
		final Label label = new Label(compo, SWT.NONE);
		label.setText("no file selected");
		
		Button browse = new Button(compo, SWT.NONE);
		browse.setText("Browse...");
		browse.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("pressed");
				FileDialog fDialog = new FileDialog(compo.getShell(), SWT.NONE);
				fDialog.setFilterNames(new String[] {"All Files (*.*)"});
				fDialog.setFilterExtensions(new String[] {"*.*"});
				String selection = fDialog.open();
				if (selection != null) {
					label.setText(selection);
					compo.layout();
					setPageComplete(true);
				}
			}
			@Override public void widgetDefaultSelected(SelectionEvent e) { }
		});
		
		setControl(compo);
	}

}
