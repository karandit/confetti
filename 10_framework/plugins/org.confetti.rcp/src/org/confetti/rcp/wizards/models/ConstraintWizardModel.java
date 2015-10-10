package org.confetti.rcp.wizards.models;

import java.util.HashMap;
import java.util.Map;

import org.confetti.core.ConstraintAttributes;
import org.confetti.rcp.constraints.ConstraintField;
import org.confetti.rcp.extensions.ConstraintDescr;
import org.eclipse.swt.widgets.Control;

public class ConstraintWizardModel {

	private final ConstraintDescr descr;
	private final ConstraintAttributes attrs;
	private final Map<ConstraintField, Control> controls = new HashMap<>();
	
	
	public ConstraintWizardModel(ConstraintDescr descr, ConstraintAttributes attrs) {
		this.descr = descr;
		this.attrs = attrs;
	}
	
	public ConstraintDescr getDescr() 					{ return descr; }
	public ConstraintAttributes getAttrs() 				{ return attrs; }
	public Map<ConstraintField, Control> getControls() 	{ return controls; }

	public void putControl(ConstraintField field, Control ctrl) {
		controls.put(field, ctrl);
	}

}
