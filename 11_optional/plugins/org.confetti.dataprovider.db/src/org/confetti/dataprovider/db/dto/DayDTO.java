package org.confetti.dataprovider.db.dto;

import org.confetti.core.Day;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

public class DayDTO implements Day {

    private final ValueMutator<String> name;

    public DayDTO(String name) {
        this.name = new ValueMutator<>(this, name);
    }

    @Override
    public ObservableValue<String> getName() {
        return name.getObservableValue();
    }

}