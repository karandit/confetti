package org.confetti.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ConstraintAttributes implements Iterable<ConstraintAttribute<?>>{
	
	private Map<String, ConstraintAttribute<?>> attrs = new HashMap<>();

	public ConstraintAttributes withInteger(final String key, final int value) {
		attrs.put(key, new ConstraintAttribute<Integer>(key, value));
		return this;
	}
	
	public ConstraintAttributes withDouble(final String key, final double value) {
		attrs.put(key, new ConstraintAttribute<Double>(key, value));
		return this;
	}

	public ConstraintAttributes withBoolean(final String key, final boolean value) {
		attrs.put(key, new ConstraintAttribute<Boolean>(key, value));
		return this;
	}

	public ConstraintAttributes withTeacher(String key, Teacher teacher) {
		attrs.put(key, new ConstraintAttribute<Teacher>(key, teacher));
		return this;
	}

	public ConstraintAttributes withStudentGroup(String key, StudentGroup sg) {
		attrs.put(key, new ConstraintAttribute<StudentGroup>(key, sg));
		return this;
	}

	public ConstraintAttributes withAssignment(String key, Assignment ass) {
		attrs.put(key, new ConstraintAttribute<Assignment>(key, ass));
		return this;
	}

	public ConstraintAttributes withRoom(String key, Room room) {
		attrs.put(key, new ConstraintAttribute<Room>(key, room));
		return this;
	}

	public ConstraintAttributes withSubject(String key, Subject subject) {
		attrs.put(key, new ConstraintAttribute<Subject>(key, subject));
		return this;
	}

	@SuppressWarnings("unchecked")
	public Integer asInteger(final String key) {
		checkKeyExistence(key);
		return ((ConstraintAttribute<Integer>) attrs.get(key)).getValue();
	}
	
	@SuppressWarnings("unchecked")
	public Double asDouble(final String key) {
		checkKeyExistence(key);
		return ((ConstraintAttribute<Double>) attrs.get(key)).getValue();
	}

	@SuppressWarnings("unchecked")
	public boolean asBoolean(final String key) {
		checkKeyExistence(key);
		return ((ConstraintAttribute<Boolean>) attrs.get(key)).getValue();
	}

	@SuppressWarnings("unchecked")
	public Teacher asTeacher(String key) {
		checkKeyExistence(key);
		return ((ConstraintAttribute<Teacher>) attrs.get(key)).getValue();
	}

	@Override
	public Iterator<ConstraintAttribute<?>> iterator() {
		return attrs.values().iterator();
	}
	//------------------------------- helpers --------------------------------------------------------------------------
	private void checkKeyExistence(final String key) {
		if (!attrs.containsKey(key)) {
			throw new RuntimeException("Value not found for key " + key);
		}
	}

}
