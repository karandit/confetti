package org.confetti.dummy;

import org.confetti.core.Day;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

/**
 * @author Gabor Bubla
 */
public class DummyDay implements Day {

	private final ValueMutator<String> name;
	
	public DummyDay(String name) {
		this.name = new ValueMutator<>(this, name);
	}
	
	@Override
	public ObservableValue<String> getName() {
		return this.name.getObservableValue();
	}

}
