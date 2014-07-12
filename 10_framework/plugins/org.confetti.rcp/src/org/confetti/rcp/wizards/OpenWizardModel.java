package org.confetti.rcp.wizards;

import java.util.List;

import org.confetti.rcp.extensions.OpenWizardDescr;

public class OpenWizardModel {

	private List<OpenWizardDescr> extensions;
	private OpenWizardDescr selectedExtension;

	public OpenWizardModel(List<OpenWizardDescr> extensions) {
		this.extensions = extensions;
	}

	public List<OpenWizardDescr> getExtensions() { return extensions; }

	public OpenWizardDescr getSelectedExtension() { return selectedExtension; }
	public void setSelectedExtension(OpenWizardDescr descr) { this.selectedExtension = descr; }
}
