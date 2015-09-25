package org.confetti.rcp.wizards.pages;

import org.confetti.rcp.extensions.OpenWizardDescr;
import org.confetti.rcp.nls.Messages;
import org.confetti.rcp.wizards.models.OpenTimetableModel;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class ChooseDataProviderWizardPage extends WizardPage {
	
	private OpenTimetableModel model;

	public ChooseDataProviderWizardPage(OpenTimetableModel model) {
		super("chooseDataProvider"); //$NON-NLS-1$
		this.model = model;
		setTitle(Messages.ChooseDataProviderWizardPage_Title);
		setDescription(Messages.ChooseDataProviderWizardPage_Description);
	}

	@Override
	public void createControl(Composite parent) {
		ListViewer viewer = new ListViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		viewer.setContentProvider(new ArrayContentProvider());
		GridDataFactory.fillDefaults().applyTo(viewer.getControl());
		
		viewer.addSelectionChangedListener((SelectionChangedEvent event) -> {
				setPageComplete(!event.getSelection().isEmpty());
				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				model.setSelectedExtension(sel.isEmpty() ? null : (OpenWizardDescr) sel.getFirstElement());
		});
		viewer.setInput(model.getExtensions());
		setPageComplete(!viewer.getSelection().isEmpty());
		
		setControl(viewer.getControl());
	}
	
}
