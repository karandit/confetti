package org.confetti.tablix.dataprovider;

import org.confetti.core.Day;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

/**
 * @author Kárándi Tamás
 */
class TablixDay implements Day {

	private final ValueMutator<String> name;
	public TablixDay(String name) {
		this.name = new ValueMutator<>(this, name);
	}
	
	@Override public ObservableValue<String> getName() { return name.getObservableValue(); }

}
