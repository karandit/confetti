package org.confetti.rcp.wizards;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.confetti.rcp.extensions.OpenWizardDescr;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

public class OpenWizard extends Wizard {

	private OpenWizardModel model;
	private ChooseDataProviderWizardPage chooserPage;
	private Map<OpenWizardDescr, List<IWizardPage>> pageCache = new HashMap<>();
	
	
	public OpenWizard(OpenWizardModel model) {
		this.model = model;
		setWindowTitle("Open");
		setForcePreviousAndNextButtons(true);
	}
	
	@Override
	public void addPages() {
		chooserPage = new ChooseDataProviderWizardPage(model);
		addPage(chooserPage);
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == chooserPage) {
			if (model.getSelectedExtension() == null) {
				return null;
			}
			OpenWizardDescr selectedExtension = model.getSelectedExtension();
			List<IWizardPage> pages = getPages(selectedExtension);
			if (!pages.isEmpty()) {
				return pages.iterator().next();
			}
		}
		
		return super.getNextPage(page);
	}
	
	private List<IWizardPage> getPages(OpenWizardDescr descr) {
		if (pageCache.containsKey(descr)) {
			return pageCache.get(descr);
		}
		List<IWizardPage> pages = descr.getFactory().getPages();
		pageCache.put(descr, pages);
		return pages;
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
