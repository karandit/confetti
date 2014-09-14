package org.confetti.dummy;

import org.confetti.core.Hour;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

/**
 * @author Gabor Bubla
 */
public class HourImpl implements Hour {

	private final ValueMutator<String> name;
	
	public HourImpl(String name) {
		this.name = new ValueMutator<>(this, name);
	}
	
	@Override
	public ObservableValue<String> getName() {
		return this.name.getObservableValue();
	}

}
