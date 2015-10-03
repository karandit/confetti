package org.confetti.fet.dataprovider;

import org.confetti.core.Day;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

/**
 * @author Kárándi Tamás
 */
class FETDay implements Day {

	private final ValueMutator<String> name;
	public FETDay(String name) {
		this.name = new ValueMutator<>(this, name);
	}
	
	@Override
	public ObservableValue<String> getName() {
		return name.getObservableValue();
	}

}
