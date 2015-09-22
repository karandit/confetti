package org.confetti.dataprovider.xml;

import org.confetti.core.Subject;

/**
 * @author K�r�ndi Tam�s
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
