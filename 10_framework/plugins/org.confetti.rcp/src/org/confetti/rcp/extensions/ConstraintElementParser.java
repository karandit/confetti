package org.confetti.rcp.extensions;

import org.eclipse.core.runtime.IConfigurationElement;

public enum ConstraintElementParser {
	Category 	{ @Override public IConstraintElement parse(IConfigurationElement e) { return new ConstraintCategoryDescr(e); } },
	Separator 	{ @Override public IConstraintElement parse(IConfigurationElement e) { return ConstraintSeparator.INSTANCE; } },
	Constraint 	{ @Override public IConstraintElement parse(IConfigurationElement e) { return new ConstraintDescr(e); } };
	
	public abstract IConstraintElement parse(IConfigurationElement e);
	
	public static ConstraintElementParser getByName(final String name) {
		switch (name.toLowerCase()) {
		case "category": 		return Category;
		case "constraint": 		return Constraint;
		default : 				return Separator;
		}
	}
}
