package org.confetti.rcp.extensions;

public enum ConstraintSeparator implements IConstraintElement {
	INSTANCE;

	@Override public String getName() 					{ return "--"; } //$NON-NLS-1$
	@Override public IConstraintElement[] getChildren() { return null; }
	@Override public boolean hasChildren() 				{ return false; }
}
