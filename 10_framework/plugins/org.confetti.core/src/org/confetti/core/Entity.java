package org.confetti.core;

public interface Entity extends Nameable, Assignable {
	<R, P> R accept(EntityVisitor<R, P> visitor, P param);
}
