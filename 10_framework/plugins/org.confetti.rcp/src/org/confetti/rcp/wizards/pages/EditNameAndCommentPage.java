package org.confetti.rcp.wizards.pages;

import static org.confetti.rcp.ConfettiPlugin.getImageDescriptor;

import org.confetti.rcp.nls.Messages;
import org.confetti.rcp.wizards.models.EditNameAndCommentModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Bubla Gabor
 */
public class EditNameAndCommentPage extends ModelableWizardPage<EditNameAndCommentModel> 
implements IWizardPageNavigatable {

	private Text txtName;
	private Text txtComment;
	
	public EditNameAndCommentPage(EditNameAndCommentModel model) {
		super("pageName", Messages.General_Name, getImageDescriptor(model.getImageKey()), model); //$NON-NLS-1$
		setDescription(Messages.EditNameAndCommentPage_Description);
	}

	@Override
	public void pageShowed() { }

	@Override
	public void pageHid() {
		getModel().setName(txtName.getText());
		getModel().setComment(txtComment.getText());
	}

	@Override
	public void createControl(Composite parent) {
		Composite compo = new Composite(parent, SWT.NONE);
		compo.setLayout(new GridLayout(1, false));
		
		//Name part with listener: if its empty the page isn't complete
		Label name = new Label(compo, SWT.NONE);
		name.setText(Messages.General_Name);
		txtName = new Text(compo, SWT.BORDER);
		txtName.setText(getSafe(getModel().getName()));
		setPageComplete(!txtName.getText().isEmpty());
		txtName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtName.addModifyListener(e -> setPageComplete(!txtName.getText().isEmpty()));
		
		//Comment part
		Label cmnt = new Label(compo, SWT.NONE);
		cmnt.setText(Messages.General_Comment);
		txtComment = new Text(compo, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		txtComment.setText(getSafe(getModel().getComment()));
		txtComment.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		setControl(compo);
	}

	private static String getSafe(final String value) {
		return value == null ? "" : value; //$NON-NLS-1$
	}
}
