package org.confetti.rcp.wizards;

import java.util.List;

import org.confetti.rcp.extensions.DataProviderDescr;
import org.confetti.rcp.extensions.DataProviderRegistry;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

public class ChooseDataProviderWizardPage extends WizardPage {
	
	public ChooseDataProviderWizardPage() {
		super("chooseDataProvider");
		setTitle("Open");
		setDescription("Choose a data provider");
	}

	@Override
	public void createControl(Composite parent) {
		List<DataProviderDescr> dataProviders = DataProviderRegistry.INSTANCE.getDataProviders(); 
				
		ListViewer dataProviderViewer = new ListViewer(parent);
		dataProviderViewer.setContentProvider(new ArrayContentProvider());
		dataProviderViewer.setInput(dataProviders);
		GridDataFactory.fillDefaults().applyTo(dataProviderViewer.getControl());
		
		setControl(dataProviderViewer.getControl());
	}

}
