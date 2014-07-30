package org.confetti.observable;

import java.util.LinkedList;
import java.util.List;

public class ObservableValue<T> {

	private T value;
	private List<ObservableListener<T>> listeners = new LinkedList<>();
	
	ObservableValue() {
	}

	void setValue(T newValue) {
		if (!areEqual(newValue, value)) {
			notifyListeners(value, newValue);
			value = newValue;
		}
	}
	
	public T getValue() { return value; }
	
	public void attachListener(ObservableListener<T> listener) {
		listeners.add(listener);
	}
	
	public void detachListener(ObservableListener<T> listener) {
		listeners.remove(listener);
	}
	
	private void notifyListeners(T oldValue, T newValue) {
		for (ObservableListener<T> listener : listeners) {
			listener.valueChanged(oldValue, newValue);
		}
	}
	

	private static <T> boolean areEqual(T valueA, T valueB) {
		return valueB == null ? valueA == null : valueB.equals(valueA);
	}

}
