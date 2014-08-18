package org.confetti.rcp.wizards.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.confetti.rcp.wizards.models.InsertEntriesModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class InsertEntriesWizardPage extends ModelableWizardPage<InsertEntriesModel> 
implements IWizardPageNavigatable {

	private Text text;
	
	public InsertEntriesWizardPage(InsertEntriesModel model) {
		super("Input", model.getInsertEntriesPageTitle(), null, model);
		setDescription(getModel().getInsertEntriesPageDescription());
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		text = new Text(parent, SWT.MULTI| SWT.BORDER | SWT.V_SCROLL);
		text.addModifyListener(new ModifyListener() {
			//TODO next,back bug fix
			@Override public void modifyText(ModifyEvent e) { setPageComplete(!text.getText().isEmpty()); }
		});
		setControl(text);
	}

	@Override
	public void pageShowed() {
	}

	@Override
	public void pageHid() {
		getModel().addEntries(extractNames(text.getText()));
	}
	
	private static List<String> extractNames(String text) {
		String[] lines = text.split("\\r?\\n");
		List<String> names = new ArrayList<>(Arrays.asList(lines));
		names.removeAll(Arrays.asList(""));
		List<String> trimmedNames = new LinkedList<>();
		for (String name : names) {
			trimmedNames.add(name.trim());
		}
		return trimmedNames;
	}

}
