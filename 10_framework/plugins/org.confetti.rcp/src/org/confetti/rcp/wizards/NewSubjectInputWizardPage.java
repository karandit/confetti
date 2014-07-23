package org.confetti.rcp.wizards;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class NewSubjectInputWizardPage extends WizardPage {

	private Text text;
	private String[] lines;

	public NewSubjectInputWizardPage(String[] lines) {
		super("Input", "Every new line will be a new Subject!", null);
		this.lines = lines;
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
//		Composite compo = new Composite(parent, SWT.NONE);
//		compo.setEnabled(true);
		text = new Text(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		text.setEnabled(true);
		text.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				if (text.getCharCount() == 0) {
					setPageComplete(false);
				} else {
					setPageComplete(true);
				}
			}
		});
		setControl(parent);
	}

	@Override
	public IWizardPage getNextPage() {
		convertText();
		return super.getNextPage();
	}

	private void convertText() {
		String[] lines = text.getText().split("\\r?\\n");
//		for (String line : lines) {
//			System.out.println(line);
//		}
	}

	public String[] getLines() {
		return lines;
	}

}
