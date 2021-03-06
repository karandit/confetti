package org.confetti.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import org.confetti.util.Triple;
import org.confetti.util.Tuple;

public class ConstraintAttributes implements Iterable<ConstraintAttribute<?>>{
	
	//------------------------------- fields ---------------------------------------------------------------------------
	private final Map<String, ConstraintAttribute<?>> attrs = new HashMap<>();

	//------------------------------- Constructors ---------------------------------------------------------------------
	public ConstraintAttributes() {
		
	}

	public ConstraintAttributes(final ConstraintAttributes origin) {
		this.attrs.putAll(origin.attrs);
	}
	//------------------------------- Iterable's methods ---------------------------------------------------------------
	@Override
	public Iterator<ConstraintAttribute<?>> iterator() {
		return attrs.values().iterator();
	}

	//------------------------------- withXXX methods ------------------------------------------------------------------
	public ConstraintAttributes withInteger(final String key, final Integer v) 			{ return withT(key, v); }
	public ConstraintAttributes withDouble(final String key, final Double v) 			{ return withT(key, v); }
	public ConstraintAttributes withBoolean(final String key, final Boolean v) 			{ return withT(key, v); }
	public ConstraintAttributes withDay(final String key, final Integer v) 				{ return withT(key, v); }
	public ConstraintAttributes withHour(final String key, final Integer v) 			{ return withT(key, v); }
	public ConstraintAttributes withWeek(String key, Iterable<Tuple<Day, Hour>> v) 		{ return withT(key, v); }
	public ConstraintAttributes withPeriod(String key, Tuple<Day, Hour> v)				{ return withT(key, v); }
	public ConstraintAttributes withInterval(String key, Tuple<Hour, Hour> v)			{ return withT(key, v); }
	public ConstraintAttributes withSubject(String key, Subject v) 						{ return withT(key, v); }
	public ConstraintAttributes withTeacher(String key, Teacher v) 						{ return withT(key, v); }
	public ConstraintAttributes withStudentGroup(String key, StudentGroup v) 			{ return withT(key, v); }
	public ConstraintAttributes withRoom(String key, Room v) 							{ return withT(key, v); }
	public ConstraintAttributes withRoomsSet(String key, Iterable<Room> v) 				{ return withT(key, v); }
	public ConstraintAttributes withAssignment(String key, Assignment v) 				{ return withT(key, v); }
	public ConstraintAttributes withAssignmentsSet(String key, Iterable<Assignment> v) 	{ return withT(key, v); }
	public ConstraintAttributes withAssignmentsCriteria(String key, Triple<Subject, Teacher, StudentGroup> v) 	{ return withT(key, v); }
	public ConstraintAttributes withTag(String key, Tag v) 								{ return withT(key, v); }

	//------------------------------- asXXX methods --------------------------------------------------------------------
	public ConstraintAttribute<?> asAttribute(final String key) 						{ return attrs.get(key); } 
	public Integer asInteger(final String key) 											{ return asT(key); }
	public Double asDouble(final String key) 											{ return asT(key); }
	public Boolean asBoolean(final String key) 											{ return asT(key); }
	public Integer asDay(String key) 													{ return asT(key); }
	public Integer asHour(String key) 													{ return asT(key); }
	public Iterable<Tuple<Day, Hour>> asWeek(final String key) 							{ return asT(key); }
	public Tuple<Day, Hour> asPeriod(final String key) 									{ return asT(key); }
	public Tuple<Hour, Hour> asInterval(final String key) 								{ return asT(key); }
	public Subject asSubject(String key)  												{ return asT(key); }
	public Teacher asTeacher(String key)  												{ return asT(key); }
	public StudentGroup asStudentGroup(String key)  									{ return asT(key); }
	public Room asRoom(String key)  													{ return asT(key); }
	public Iterable<Room> asRoomsSet(String key)										{ return asT(key); }
	public Assignment asAssignment(String key)											{ return asT(key); }
	public Iterable<Assignment> asAssignmentsSet(String key)							{ return asT(key); }
	public Triple<Subject, Teacher, StudentGroup> asAssignmentsCriteria(String key) 	{ return asT(key); }
	public Tag asTag(String key)  														{ return asT(key); }
	
	//------------------------------- asMaybeXXX methods ---------------------------------------------------------------
	public Optional<Tag> asMaybeTag(String key)  										{ return asMaybeT(key); }
	 
	//------------------------------- helpers --------------------------------------------------------------------------
	private <T> T asT(final String key) {
		if (!attrs.containsKey(key)) {
			throw new RuntimeException("Value not found for key " + key);
		}
		@SuppressWarnings("unchecked")
		T value = ((ConstraintAttribute<T>) attrs.get(key)).getValue();
		return value;
	}
	
	private <T> Optional<T> asMaybeT(final String key) {
		if (!attrs.containsKey(key)) {
			return Optional.empty();
		}
		@SuppressWarnings("unchecked")
		T value = ((ConstraintAttribute<T>) attrs.get(key)).getValue();
		return Optional.ofNullable(value);
	}
	
	private <T> ConstraintAttributes withT(final String key, final T value) {
		attrs.put(key, new ConstraintAttribute<>(key, value));
		return this;
	}

}
