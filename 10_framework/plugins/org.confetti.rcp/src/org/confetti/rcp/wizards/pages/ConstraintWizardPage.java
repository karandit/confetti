package org.confetti.rcp.wizards.pages;

import org.confetti.core.ConstraintAttribute;
import org.confetti.rcp.constraints.ConstraintField;
import org.confetti.rcp.constraints.FieldTypeApplyLayoutVisitor;
import org.confetti.rcp.constraints.FieldTypeCreateControlVisitor;
import org.confetti.rcp.wizards.models.ConstraintWizardModel;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class ConstraintWizardPage extends ModelableWizardPage<ConstraintWizardModel> implements IWizardPageNavigatable {

	public ConstraintWizardPage(ConstraintWizardModel model) {
		super("constraint-details", "Constraint", null, model);
		setDescription(model.getDescr().getName());
	}

	@Override public void pageShowed() { }
	@Override public void pageHid() { }

	@Override
	public void createControl(Composite parent) {
		Composite area = new Composite(parent, SWT.NONE);
        area.setLayout(new GridLayout(2, false));
        for (ConstraintField field : getModel().getDescr().getFields()) {
            Label label = new Label(area, SWT.NONE);
            label.setText(field.getLabel());
            GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.TOP).applyTo(label);
            ConstraintAttribute<?> attribute = getModel().getAttrs().asAttribute(field.getName());
            
            Control ctrl = field.getType().accept(FieldTypeCreateControlVisitor.INSTANCE, area, attribute);
            field.getType().accept(FieldTypeApplyLayoutVisitor.INSTANCE, ctrl, null);
            getModel().putControl(field, ctrl);
        }

		setControl(area);
	}

}
