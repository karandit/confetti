package org.confetti.core;

import java.util.HashMap;
import java.util.Map;

public class ConstraintAttributes {
	
	private Map<String, ConstraintAttribute<?>> attrs = new HashMap<>();

	public ConstraintAttributes withDouble(final String key, final double value) {
		attrs.put(key, new ConstraintAttribute<Double>(key, value));
		return this;
	}

	public ConstraintAttributes withBoolean(final String key, final boolean value) {
		attrs.put(key, new ConstraintAttribute<Boolean>(key, value));
		return this;
	}

	public Double asDouble(final String key) {
		if (!attrs.containsKey(key)) {
			throw new RuntimeException("Value not found for key " + key);
		}
		
		ConstraintAttribute<Double> foundAttr = (ConstraintAttribute<Double>) attrs.get(key);
		return foundAttr.getValue();
	}

	public boolean asBoolean(final String key) {
		if (!attrs.containsKey(key)) {
			throw new RuntimeException("Value not found for key " + key);
		}
		
		ConstraintAttribute<Boolean> foundAttr = (ConstraintAttribute<Boolean>) attrs.get(key);
		return foundAttr.getValue();
	}

}
