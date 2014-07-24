package org.confetti.rcp.wizards;

import java.util.ArrayList;
import java.util.List;

public class NewSubjectInputWizardModel {
	private List<String> mSubjects = new ArrayList<String>();

	public List<String> getSubjects() {
		return mSubjects;
	}

	public void setSubjects(List<String> subjects) {
		this.mSubjects = subjects;
	}
}
