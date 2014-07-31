package org.confetti.rcp.wizards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * @author Bubla Gabor
 */
public class NewWizardDaysInputPage extends ModelableWizardPage<NewWizardModel> implements IWizardPageNavigatable {

	private Text text;
	
	public NewWizardDaysInputPage(NewWizardModel model) {
		super("pageName", "title", null, model);
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		text = new Text(parent, SWT.MULTI| SWT.BORDER | SWT.V_SCROLL);
		text.addModifyListener(new ModifyListener() {
			@Override public void modifyText(ModifyEvent e) { setPageComplete(!text.getText().isEmpty()); }
		});
		setControl(text);
	}

	@Override
	public void pageShowed() { }

	@Override
	public void pageHid() {
		//TODO: check multiple
	}
}