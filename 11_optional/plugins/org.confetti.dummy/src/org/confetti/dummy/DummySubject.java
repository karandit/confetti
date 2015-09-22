package org.confetti.dummy;

import org.confetti.core.Subject;

public class DummySubject extends DummyEntity implements Subject {

	public DummySubject(String name) {
		super(name);
	}
	
	@Override
	public int getColor() {
		return 0;
	}
	
}
