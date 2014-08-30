package org.confetti.observable;

public class ValueMutator<T> {
	
	private final ObservableValue<T> observValue = new ObservableValue<>();
	
	public ValueMutator() {
	}

	public ValueMutator(Object src, T initialValue) {
		observValue.setValue(src, initialValue);
	}
	
	public ObservableValue<T> getObservableValue() {
		return observValue;
	}

	public void setValue(Object src, T newValue) {
		observValue.setValue(src, newValue);
	}
}
