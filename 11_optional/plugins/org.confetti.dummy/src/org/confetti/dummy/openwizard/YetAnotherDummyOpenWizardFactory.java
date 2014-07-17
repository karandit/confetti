package org.confetti.dummy.openwizard;

import java.util.LinkedList;
import java.util.List;

import org.confetti.rcp.extensions.OpenWizardFactory;
import org.eclipse.jface.wizard.IWizardPage;

public class YetAnotherDummyOpenWizardFactory implements OpenWizardFactory {

	@Override
	public List<IWizardPage> getPages() {
		List<IWizardPage> pages = new LinkedList<>();
		pages.add(new ChooseFileWizardPage("another dummy page"));
		return pages;
	}

}
