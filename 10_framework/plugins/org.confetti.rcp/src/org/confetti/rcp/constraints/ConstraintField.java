package org.confetti.rcp.constraints;

import java.util.HashMap;
import java.util.Map;

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
    
    private final static Map<String, FieldType> fieldTypeMapping = new HashMap<>();
    
    static {
        fieldTypeMapping.put("assignment-field", FieldType.Assignment);
        fieldTypeMapping.put("assignments-set-field", FieldType.AssignmentsSet);
        fieldTypeMapping.put("assignments-criteria-field", FieldType.AssignmentsCriteria);
        fieldTypeMapping.put("boolean-field", FieldType.Boolean);
        fieldTypeMapping.put("day-field", FieldType.Day);
        fieldTypeMapping.put("hour-field", FieldType.Hour);
        fieldTypeMapping.put("double-field", FieldType.Double);
        fieldTypeMapping.put("integer-field", FieldType.Integer);
        fieldTypeMapping.put("period-field", FieldType.Period);
        fieldTypeMapping.put("interval-field", FieldType.Interval);
        fieldTypeMapping.put("teacher-field", FieldType.Teacher);
        fieldTypeMapping.put("studentgroup-field", FieldType.StudentGroup);
        fieldTypeMapping.put("week-field", FieldType.Week);
        fieldTypeMapping.put("room-field", FieldType.Room);
        fieldTypeMapping.put("rooms-set-field", FieldType.RoomsSet);
        fieldTypeMapping.put("subject-field", FieldType.Subject);
    }
    
    
    private final String name;
    private final String label;
    private final FieldType type;
    
    public ConstraintField(IConfigurationElement element) {
        this.name = element.getAttribute("name");
        this.label = element.getAttribute("label");
        this.type = fieldTypeMapping.get(element.getName());
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
