package org.confetti.rcp.wizards;

import java.util.Map;

import org.confetti.core.ConstraintAttributes;
import org.confetti.rcp.constraints.ConstraintField;
import org.confetti.rcp.constraints.FieldTypePutValueVisitor;
import org.confetti.rcp.extensions.ConstraintDescr;
import org.confetti.rcp.wizards.models.ConstraintWizardModel;
import org.confetti.rcp.wizards.pages.ConstraintWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Control;

public class ConstraintWizard  extends Wizard {

	private final ConstraintWizardModel model;

	public ConstraintWizard(ConstraintDescr constraintDescr, ConstraintAttributes newAttrs, boolean isNew) {
		model = new ConstraintWizardModel(constraintDescr, newAttrs);
		setWindowTitle(isNew ? "New Constraint" : "Update Constraint");
	}
	
	@Override
	public void addPages() {
		addPage(new ConstraintWizardPage(model));
	}
	
	@Override
	public boolean performFinish() {
    	FieldTypePutValueVisitor visitor = new FieldTypePutValueVisitor(model.getAttrs());
        for (Map.Entry<ConstraintField, Control> entry : model.getControls().entrySet()) {
        	ConstraintField field = entry.getKey();
        	field.getType().accept(visitor, field.getName(), entry.getValue());
        }
		return true;
	}

}
