package org.confetti.tablix.dataprovider;

import org.confetti.core.Subject;

/**
 * @author K�r�ndi Tam�s
 */
class TablixSubject extends TablixEntity implements Subject {

	private int color;

	public TablixSubject(String name, int color) {
		super(name);
		this.color = color;
	}

	@Override
	public int getColor() {
		return color;
	}
}
