package org.confetti.rcp.extensions;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;

/**
 * @author Gabor Bubla
 */
public class ConstraintField {
    
    private final static Map<String, FieldType> fieldTypeMapping = new HashMap<>();
    
    static {
        fieldTypeMapping.put("assignment-field", FieldType.Assignment);
        fieldTypeMapping.put("assignments-set-field", FieldType.AssignmentsSet);
        fieldTypeMapping.put("boolean-field", FieldType.Boolean);
        fieldTypeMapping.put("day-field", FieldType.Day);
        fieldTypeMapping.put("hour-field", FieldType.Hour);
        fieldTypeMapping.put("number-field", FieldType.Number);
        fieldTypeMapping.put("period-field", FieldType.Period);
        fieldTypeMapping.put("period-number-field", FieldType.PeriodNumber);
        fieldTypeMapping.put("teacher-field", FieldType.Teacher);
        fieldTypeMapping.put("week-field", FieldType.Week);
    }
    
    public enum FieldType {
        Boolean,
        Number,
        Day,
        Hour,
        Week,
        Period,
        PeriodNumber,
        Teacher,
        Assignment,
        AssignmentsSet,
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
    
    
}
