package org.confetti.core;

import org.confetti.observable.ObservableList;

public interface Constraintable {
	ObservableList<Constraint> getConstraints();
}
