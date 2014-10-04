package org.confetti.dataprovider.db.dto;

import org.confetti.core.Hour;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

public class HourDTO implements Hour {

    private final ValueMutator<String> name;

    public HourDTO(String name) {
        this.name = new ValueMutator<>(this, name);
    }

    @Override
    public ObservableValue<String> getName() {
        return name.getObservableValue();
    }

}
