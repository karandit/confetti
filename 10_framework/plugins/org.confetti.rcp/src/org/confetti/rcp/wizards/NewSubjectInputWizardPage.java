package org.confetti.rcp.wizards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class NewSubjectInputWizardPage extends ModelableWizardPage<NewSubjectInputWizardModel> implements IWizardPageNavigatable {

	private Text text;
	
	public NewSubjectInputWizardPage(NewSubjectInputWizardModel model) {
		super("Input", "Subject names", null, model);
		setDescription("Every new line will be a new subject");
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		text = new Text(parent, SWT.MULTI| SWT.BORDER | SWT.V_SCROLL);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		text.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				setPageComplete(!text.getText().isEmpty());
			}
		});
		setControl(text);
	}
	
	private List<String> linesToList(String subjectsStr) {
		String[] lines = subjectsStr.split("\\r?\\n");
		List<String> subjects = new ArrayList<>();
		for (String line : lines) {
			subjects.add(line);
		}
		return subjects;
	}

	@Override
	public void pageShowed() {
	}

	@Override
	public void pageHid() {
		String subjectsStr = text.getText();
		List<String> subjects = linesToList(subjectsStr);
		subjects.removeAll(Arrays.asList("",null));
		getModel().setSubjects(subjects);
	}

}
