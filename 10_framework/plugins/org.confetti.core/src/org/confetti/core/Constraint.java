package org.confetti.core;

import org.confetti.observable.ObservableValue;

/**
 * @author K�r�ndi Tam�s
 */
public interface Constraint {
	String getConstraintType();
	ObservableValue<ConstraintAttributes> getAttributes();
}
