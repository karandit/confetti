package org.confetti.rcp.wizards;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;

public abstract class ModelableWizardPage<T> extends WizardPage {

	private final T mModel;
	
	public ModelableWizardPage(String pageName, String title, ImageDescriptor titleImage, T model) {
		super(pageName, title, titleImage);
		mModel = model;
	}

	public ModelableWizardPage(String pageName, T model) {
		super(pageName);
		mModel = model;
	}

	public T getModel() {
		return mModel;
	}

}
