package org.confetti.observable;

public class ValueMutator<T> {
	
	private final ObservableValue<T> observValue = new ObservableValue<>();
	
	public ValueMutator() {
	}
	
	public ObservableValue<T> getObservableValue() {
		return observValue;
	}

	public void setValue(T newValue) {
		observValue.setValue(newValue);
	}
}
