package org.confetti.dummy;

import org.confetti.core.Entity;
import org.confetti.observable.ObservableListener;
import org.confetti.observable.ValueMutator;

/**
 * @author Bubla Gabor
 */
public class ListListener implements ObservableListener<ValueMutator<? extends Entity>> {

	@Override
	public void valueChanged(ValueMutator<? extends Entity> oldValue, ValueMutator<? extends Entity> newValue) {
		
	}
}
