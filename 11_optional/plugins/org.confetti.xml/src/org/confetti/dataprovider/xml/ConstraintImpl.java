package org.confetti.dataprovider.xml;

import org.confetti.core.Constraint;
import org.confetti.core.ConstraintAttributes;
import org.confetti.xml.core.BaseConstraintXml;

/**
 * @author Kárándi Tamás
 */
class ConstraintImpl implements Constraint {

	private final String type;
	private final ConstraintAttributes attrs;
	private final BaseConstraintXml xmlConstraint;
	
	public ConstraintImpl(final BaseConstraintXml xmlConstraint, final String type, final ConstraintAttributes attrs) {
		this.xmlConstraint = xmlConstraint;
		this.type = type;
		this.attrs = attrs;
	}
	
	public BaseConstraintXml getXmlConstraint() { return xmlConstraint; }
	@Override public String getConstraintType() { return type; }
	@Override public ConstraintAttributes getAttributes() { return attrs; }

	
}
