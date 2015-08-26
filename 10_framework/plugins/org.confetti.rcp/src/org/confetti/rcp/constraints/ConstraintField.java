package org.confetti.rcp.constraints;

import org.eclipse.core.runtime.IConfigurationElement;

/**
 * @author Bubla G�bor 
 * @author K�r�ndi Tam�s
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

    public String getName() 	{ return name; }
    public String getLabel() 	{ return label; }
    public FieldType getType() 	{ return type; }

}
