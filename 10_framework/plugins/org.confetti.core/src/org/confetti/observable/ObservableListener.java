package org.confetti.observable;

public interface ObservableListener<T> {
	void valueChanged(Object src, T oldValue, T newValue);
}
