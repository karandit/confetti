package org.confetti.observable;

public interface ObservableListener<T> {
	void valueChanged(T oldValue, T newValue);
}
