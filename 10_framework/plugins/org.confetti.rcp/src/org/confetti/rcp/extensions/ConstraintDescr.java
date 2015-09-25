package org.confetti.rcp.extensions;

import java.util.LinkedList;
import java.util.List;

import org.confetti.rcp.constraints.ConstraintField;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;

/**
 * @author Gabor Bubla
 */
public class ConstraintDescr implements IConstraintElement {

    private final String id;
    private final String name;
    private final String description;
    private final List<ConstraintField> fields = new LinkedList<>();

    public ConstraintDescr(IConfigurationElement element) {
        IExtension declaringExtension = element.getDeclaringExtension();
    	id = declaringExtension.getNamespaceIdentifier() + "." + element.getAttribute("id"); //$NON-NLS-1$ //$NON-NLS-2$
        name = element.getAttribute("name"); //$NON-NLS-1$
        description = element.getAttribute("description"); //$NON-NLS-1$
        for (IConfigurationElement childrenElement : element.getChildren()) {
            fields.add(new ConstraintField(childrenElement));
        }
    }

    public String getId() 								{ return id; }
    public String getDescription() 						{ return description; }
    public List<ConstraintField> getFields() 			{ return fields; }

    @Override public String getName() 					{ return name; }
	@Override public IConstraintElement[] getChildren() { return null; }
	@Override public boolean hasChildren() 				{ return false; }
    
}
