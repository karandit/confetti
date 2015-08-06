package org.confetti.rcp.extensions;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;

public class ConstraintCategoryDescr implements IConstraintElement {

	private final String name;
	private final List<IConstraintElement> items = new LinkedList<>();

	public ConstraintCategoryDescr(IConfigurationElement element) {
		name = element.getAttribute("name");
		for (IConfigurationElement child : element.getChildren()) {
			ConstraintElementParser parser = ConstraintElementParser.getByName(child.getName());
			items.add(parser.parse(child));
		}
	}

	@Override public String getName() 					{ return name; }
	@Override public IConstraintElement[] getChildren() { return items.toArray(new IConstraintElement[items.size()]); }
	@Override public boolean hasChildren() 				{ return !items.isEmpty(); }
}
