package org.confetti.dataprovider.xml;

import org.confetti.core.Building;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

public class BuildingImpl implements Building {
	
	private final ValueMutator<String> name;

	public BuildingImpl(String name) {
		this.name = new ValueMutator<>(this, name);
	}
	
	@Override public ObservableValue<String> getName() { return name.getObservableValue(); }

}
