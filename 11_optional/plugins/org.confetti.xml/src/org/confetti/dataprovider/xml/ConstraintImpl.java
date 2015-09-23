package org.confetti.dataprovider.xml;

import org.confetti.core.Constraint;
import org.confetti.core.ConstraintAttributes;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

/**
 * @author Kárándi Tamás
 */
class ConstraintImpl implements Constraint {

	private final String type;
	private final ValueMutator<ConstraintAttributes> attrs;
	
	public ConstraintImpl(final String type, final ConstraintAttributes attrs) {
		this.type = type;
		this.attrs = new ValueMutator<>(this, attrs);
	}
	
	public ValueMutator<ConstraintAttributes> getAttrsMutator() { return attrs; }
	@Override public String getConstraintType() { return type; }
	@Override public ObservableValue<ConstraintAttributes> getAttributes() { return attrs.getObservableValue(); }

}
