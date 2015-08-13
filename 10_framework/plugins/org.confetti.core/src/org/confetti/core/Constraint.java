package org.confetti.core;

/**
 * @author Kárándi Tamás
 */
public interface Constraint {
	String getConstraintType();
	ConstraintAttributes getAttributes();
}
