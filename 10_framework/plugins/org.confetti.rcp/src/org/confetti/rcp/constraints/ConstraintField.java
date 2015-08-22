package org.confetti.rcp.constraints;

import org.confetti.core.Constraint;
import org.confetti.core.ConstraintAttribute;
import org.confetti.core.ConstraintAttributes;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Bubla Gábor 
 * @author Kárándi Tamás
 */
public class ConstraintField {
    
    private final String name;
    private final String label;
    private final FieldType type;
    
    public ConstraintField(IConfigurationElement element) {
        this.name = element.getAttribute("name");
        this.label = element.getAttribute("label");
        this.type = FieldType.getByType(element.getName());
    }

    public String getName() { return name; }
    public String getLabel() { return label; }
    public FieldType getType() { return type; }

    public Control createControl(Composite area, ConstraintAttribute<?> attribute) {
        Control ctrl = getType().createControl(area, attribute);
        getType().applyLayout(ctrl);
        return ctrl;
    }
    //TODO: remove constraint
	public String printValue(ConstraintAttributes attrs, Constraint constraint) {
		try {
			attrs.asObject(name); //TODO: remove it
			return type.prettyPrint(name, attrs);
		} catch (Exception e) {
			System.out.println(constraint.getConstraintType() + " " + type + " " + name + " " + e);
			return "***";
		}
	}
    
}
