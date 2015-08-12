package org.confetti.core;

public class ConstraintAttribute<T> {
	
	private final String key;
	private final T value;

	ConstraintAttribute(final String key, final T value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() 	{ return key; } 
	public T getValue() 	{ return value; }
	
}
