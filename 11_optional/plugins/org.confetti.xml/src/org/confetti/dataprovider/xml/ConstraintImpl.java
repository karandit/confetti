package org.confetti.dataprovider.xml;

import org.confetti.core.Constraint;
import org.confetti.core.ConstraintAttributes;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;
import org.confetti.xml.core.BaseConstraintXml;

/**
 * @author Kárándi Tamás
 */
class ConstraintImpl implements Constraint {

	private final String type;
	private final ValueMutator<ConstraintAttributes> attrs;
	private final BaseConstraintXml xmlConstraint;
	
	public ConstraintImpl(final BaseConstraintXml xmlConstraint, final String type, final ConstraintAttributes attrs) {
		this.xmlConstraint = xmlConstraint;
		this.type = type;
		this.attrs = new ValueMutator<>(this, attrs);
	}
	
	public BaseConstraintXml getXmlConstraint() { return xmlConstraint; }
	public ValueMutator<ConstraintAttributes> getAttrsMutator() { return attrs; }
	@Override public String getConstraintType() { return type; }
	@Override public ObservableValue<ConstraintAttributes> getAttributes() { return attrs.getObservableValue(); }


	
}
