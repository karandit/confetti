package org.confetti.rcp.wizards;

import java.util.List;

import org.confetti.rcp.extensions.OpenWizardDescr;
import org.confetti.rcp.wizards.models.OpenTimetableModel;
import org.confetti.rcp.wizards.pages.ChooseDataProviderWizardPage;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

public class OpenTimetableWizard extends Wizard {

	private OpenTimetableModel model;
	private ChooseDataProviderWizardPage chooserPage;
//	private Map<OpenWizardDescr, List<IWizardPage>> pageCache = new HashMap<>();
	
	public OpenTimetableWizard(OpenTimetableModel model) {
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
//		if (pageCache.containsKey(descr)) {
//			return pageCache.get(descr);
//		}
//		List<IWizardPage> pages = descr.getWizard().getPages();
//		pageCache.put(descr, pages);
//		return pages;
		return null;
	}

	@Override public boolean performFinish() { return true; }

}
