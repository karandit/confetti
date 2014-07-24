package org.confetti.rcp.wizards;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class NewSubjectVerifyWizardPage extends ModelableWizardPage<NewSubjectInputWizardModel> implements IWizardPageNavigatable {

	private Label label;
	private Composite mResultComposite;
	private Table table;

	public NewSubjectVerifyWizardPage(NewSubjectInputWizardModel model) {
		super("Verify", "Summary", null, model);
		setDescription("The following subjects will be added");
	}

	@Override
	public void createControl(Composite parent) {
		// label = new Label(parent, SWT.NONE);
		table = new Table(parent, SWT.BORDER | SWT.V_SCROLL);
		TableColumn names = new TableColumn(table, SWT.NONE);
		TableColumn check = new TableColumn(table, SWT.NONE);
		//mResultComposite = new Composite(parent, SWT.BORDER | SWT.V_SCROLL);
		//mResultComposite.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		//mResultComposite.setLayout(new GridLayout(2, true));
		setControl(table);
	}

	@Override
	public void pageShowed() {
		List<String> subjects = getModel().getSubjects();
		// for (String string : subjects) {
		// label.setText(label.getText() + '\n' + string);
		// }
		for (String s : subjects) {
			
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, s);
			TableItem ok = new TableItem(table, SWT.NONE);
			ok.setText(1, "ok");
			
//			Label nameLabel = new Label(mResultComposite, SWT.NONE);
//			nameLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true));
//			nameLabel.setText(s);
//
//			Label okLabel = new Label(mResultComposite, SWT.NONE);
//			okLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, true));
//			okLabel.setText("ok");
		}
		table.pack();
		//mResultComposite.pack();
	}

	@Override
	public void pageHid() {
	}
}
