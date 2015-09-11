package org.confetti.dataprovider.xml;

import org.confetti.core.EntityVisitor;
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
	public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
		return visitor.visitSubject(this, param);
	}

	@Override
	public int getColor() {
		return color;
	}
}
