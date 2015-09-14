package org.confetti.core;

import org.confetti.observable.ObservableList;

public interface Tag extends Nameable {
	ObservableList<Assignment> getAssignments();
}
