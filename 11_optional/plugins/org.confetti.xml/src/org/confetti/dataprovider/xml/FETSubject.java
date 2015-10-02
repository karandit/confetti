package org.confetti.dataprovider.xml;

import org.confetti.core.Subject;

/**
 * @author Kárándi Tamás
 */
class FETSubject extends FETEntity implements Subject {

	private int color;

	public FETSubject(String name, int color) {
		super(name);
		this.color = color;
	}

	@Override
	public int getColor() {
		return color;
	}
}
