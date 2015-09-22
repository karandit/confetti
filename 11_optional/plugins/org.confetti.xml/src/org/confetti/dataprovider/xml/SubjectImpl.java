package org.confetti.dataprovider.xml;

import org.confetti.core.Subject;

/**
 * @author Kárándi Tamás
 */
class SubjectImpl extends EntityImpl implements Subject {

	private int color;

	public SubjectImpl(String name, int color) {
		super(name);
		this.color = color;
	}

	@Override
	public int getColor() {
		return color;
	}
}
