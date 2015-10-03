package org.confetti.tablix.dataprovider;

import org.confetti.core.Hour;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

/**
 * @author Kárándi Tamás
 */
class TablixHour implements Hour {

	private final ValueMutator<String> name;
	public TablixHour(String name) {
		this.name = new ValueMutator<>(this, name);
	}
	
	@Override public ObservableValue<String> getName() { return name.getObservableValue(); }

}
