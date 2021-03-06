package org.confetti.fet.dataprovider;

import org.confetti.core.Subject;

/**
 * @author K�r�ndi Tam�s
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
