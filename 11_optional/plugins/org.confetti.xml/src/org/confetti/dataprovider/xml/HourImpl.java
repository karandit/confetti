package org.confetti.dataprovider.xml;

import org.confetti.core.Hour;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

/**
 * @author Kárándi Tamás
 */
class HourImpl implements Hour {

	private final ValueMutator<String> name;
	public HourImpl(String name) {
		this.name = new ValueMutator<>(this, name);
	}
	
	@Override
	public ObservableValue<String> getName() {
		return name.getObservableValue();
	}

}
