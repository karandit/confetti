package org.confetti.rcp.extensions;

/**
 * @author Bubla Gabor
 *
 */
public interface IConstraintElement {
	String getName();
	IConstraintElement[] getChildren();
	boolean hasChildren();
}
