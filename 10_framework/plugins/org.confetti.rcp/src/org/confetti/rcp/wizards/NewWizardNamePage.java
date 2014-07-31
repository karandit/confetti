package org.confetti.rcp.wizards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Bubla Gabor
 */
public class NewWizardNamePage extends ModelableWizardPage<NewWizardModel> implements IWizardPageNavigatable {

	private Text instituteName;
	private Text comment;
	
	private Composite compo;

	public NewWizardNamePage(NewWizardModel model) {
		super("pageName", "title", null, model);
		setPageComplete(false);
	}

	@Override
	public void pageShowed() { }

	@Override
	public void pageHid() {
		getModel().setInstituteName(instituteName.getText());
		getModel().setComment(comment.getText());
	}

	@Override
	public void createControl(Composite parent) {
		compo = new Composite(parent, SWT.NONE);
		compo.setLayout(new GridLayout(1, false));
		
		//Name part with listener: if its empty the page isn't complete
		Label name = new Label(compo, SWT.NONE);
		name.setText("Name");
		instituteName = new Text(compo, SWT.BORDER);
		instituteName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		instituteName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (instituteName.getText().isEmpty()) {
					setPageComplete(false);
				} else {
					setPageComplete(true);
				}
			}
		});
		
		//Comment part
		Label cmnt = new Label(compo, SWT.NONE);
		cmnt.setText("Comment");
		comment = new Text(compo, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		comment.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		setControl(compo);
	}
}
