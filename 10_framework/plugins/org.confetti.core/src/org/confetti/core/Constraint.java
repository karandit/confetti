package org.confetti.core;

import org.confetti.observable.ObservableValue;

/**
 * @author Kárándi Tamás
 */
public interface Constraint {
	String getConstraintType();
	ObservableValue<ConstraintAttributes> getAttributes();
}
