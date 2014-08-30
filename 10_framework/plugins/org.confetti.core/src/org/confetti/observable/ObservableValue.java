package org.confetti.observable;

import java.util.LinkedList;
import java.util.List;

public class ObservableValue<T> {

	private T value;
	private List<ObservableListener<T>> listeners = new LinkedList<>();
	
	ObservableValue() {
	}

	void setValue(Object src, T newValue) {
		if (!areEqual(newValue, value)) {
			value = newValue;
			notifyListeners(src, value, newValue);
		}
	}
	
	public T getValue() { return value; }
	
	public void attachListener(ObservableListener<T> listener) {
		listeners.add(listener);
	}
	
	public void detachListener(ObservableListener<T> listener) {
		listeners.remove(listener);
	}
	
	private void notifyListeners(Object src, T oldValue, T newValue) {
		for (ObservableListener<T> listener : listeners) {
			listener.valueChanged(src, oldValue, newValue);
		}
	}
	
	private static <T> boolean areEqual(T valueA, T valueB) {
		return valueB == null ? valueA == null : valueB.equals(valueA);
	}

}
