package org.confetti.rcp.views;

import org.confetti.core.Nameable;
import org.confetti.observable.ObservableValue;
import org.confetti.rcp.ConfettiPlugin;

public enum Root implements Nameable {
	All;
	
	@Override
	public ObservableValue<String> getName() {
		return ConfettiPlugin.getDefault().getDataProvider().getValue().getName();
	}
}
