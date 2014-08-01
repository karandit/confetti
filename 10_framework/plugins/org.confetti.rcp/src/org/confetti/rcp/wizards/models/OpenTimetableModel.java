package org.confetti.rcp.wizards.models;

import java.util.List;

import org.confetti.rcp.extensions.OpenWizardDescr;

public class OpenTimetableModel {

	private List<OpenWizardDescr> extensions;
	private OpenWizardDescr selectedExtension;

	public OpenTimetableModel(List<OpenWizardDescr> extensions) {
		this.extensions = extensions;
	}

	public List<OpenWizardDescr> getExtensions() { return extensions; }

	public OpenWizardDescr getSelectedExtension() { return selectedExtension; }
	public void setSelectedExtension(OpenWizardDescr descr) { this.selectedExtension = descr; }
}
