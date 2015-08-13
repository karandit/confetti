package org.confetti.dummy;

import org.confetti.core.EntityVisitor;
import org.confetti.core.Subject;

public class DummySubject extends DummyEntity implements Subject {

	public DummySubject(String name) {
		super(name);
	}
	
	@Override
	public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
		return visitor.visitSubject(this, param);
	}
	
}
