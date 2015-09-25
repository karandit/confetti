package org.confetti.rcp.constraints;

import org.eclipse.core.runtime.IConfigurationElement;

/**
 * @author Bubla Gábor 
 * @author Kárándi Tamás
 */
public class ConstraintField {
    
    private final String name;
    private final String label;
    private final FieldType type;
    
    public ConstraintField(IConfigurationElement element) {
        this.name = element.getAttribute("name"); //$NON-NLS-1$
        this.label = element.getAttribute("label"); //$NON-NLS-1$
        this.type = FieldType.getByType(element.getName());
    }

    public String getName() 	{ return name; }
    public String getLabel() 	{ return label; }
    public FieldType getType() 	{ return type; }

}
