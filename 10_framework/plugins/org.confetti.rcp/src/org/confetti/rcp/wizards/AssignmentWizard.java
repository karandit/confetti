package org.confetti.rcp.wizards;

import org.confetti.core.DataPersister;
import org.confetti.core.DataProvider;
import org.confetti.rcp.wizards.models.AssignmentModel;
import org.confetti.rcp.wizards.pages.AssignmentParticipantWizardPage;
import org.confetti.rcp.wizards.pages.IWizardPageNavigatable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

public class AssignmentWizard extends Wizard {

	private final AssignmentModel model;
	private final DataPersister dataPersister;
	
	public AssignmentWizard(DataProvider dp, DataPersister dataPersister) {
		this.dataPersister = dataPersister;
		this.model = new AssignmentModel(dp);
		setWindowTitle("New assignment");
	}

	@Override
	public void addPages() {
		addPage(new AssignmentParticipantWizardPage(model));
	}

	@Override
	public boolean performFinish() {
		//Put the selections into the model
		((IWizardPageNavigatable) getPages()[0]).pageHid(); 

        //warning dialog if a viewer has no selection
        if (model.getSubjects().isEmpty() || model.getTeachers().isEmpty() || model.getStudentGroups().isEmpty()) {
            MessageDialog.openWarning(this.getShell(), 
                    "Wrong selection", "Please select a subject, one or more teacher and one or more student group.");
            return false;
        }
        
        //create the assignment
        dataPersister.addAssignment(model.getSubjects().get(0), model.getTeachers(), model.getStudentGroups());
		return true;
	}
}
