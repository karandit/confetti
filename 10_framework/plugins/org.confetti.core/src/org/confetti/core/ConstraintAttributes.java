package org.confetti.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.confetti.util.Triple;
import org.confetti.util.Tuple;

public class ConstraintAttributes implements Iterable<ConstraintAttribute<?>>{
	
	//------------------------------- fields ---------------------------------------------------------------------------
	private final Map<String, ConstraintAttribute<?>> attrs = new HashMap<>();

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
	public ConstraintAttributes withSubject(String key, Subject v) 						{ return withT(key, v); }
	public ConstraintAttributes withTeacher(String key, Teacher v) 						{ return withT(key, v); }
	public ConstraintAttributes withStudentGroup(String key, StudentGroup v) 			{ return withT(key, v); }
	public ConstraintAttributes withRoom(String key, Room v) 							{ return withT(key, v); }
	public ConstraintAttributes withRoomsSet(String key, Iterable<Room> v) 				{ return withT(key, v); }
	public ConstraintAttributes withAssignment(String key, Assignment v) 				{ return withT(key, v); }
	public ConstraintAttributes withAssignmentsSet(String key, Iterable<Assignment> v) 	{ return withT(key, v); }
	public ConstraintAttributes withAssignmentsCriteria(String key, Triple<Subject, Teacher, StudentGroup> v) 	{ return withT(key, v); }
	public ConstraintAttributes withPeriod(String key, Object v) 						{ return withT(key, v); }

	//------------------------------- asXXX methods --------------------------------------------------------------------
	//TODO: remove this method
	public Object asObject(final String key) 											{ return asT(key); }
	public Integer asInteger(final String key) 											{ return asT(key); }
	public Double asDouble(final String key) 											{ return asT(key); }
	public Boolean asBoolean(final String key) 											{ return asT(key); }
	public Iterable<Tuple<Day, Hour>> asWeek(final String key) 							{ return asT(key); }
	public Subject asSubject(String key)  												{ return asT(key); }
	public Teacher asTeacher(String key)  												{ return asT(key); }
	public StudentGroup asStudentGroup(String key)  									{ return asT(key); }
	public Room asRoom(String key)  													{ return asT(key); }
	public Iterable<Room> asRoomsSet(String key)										{ return asT(key); }
	public Assignment asAssignment(String key)											{ return asT(key); }
	public Iterable<Assignment> asAssignmentSet(String key)								{ return asT(key); }
	public Triple<Subject, Teacher, StudentGroup> asAssignmentsCriteria(String key) 	{ return asT(key); }
	 
	//------------------------------- helpers --------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	private <T> T asT(final String key) {
		if (!attrs.containsKey(key)) {
			throw new RuntimeException("Value not found for key " + key);
		}
		return ((ConstraintAttribute<T>) attrs.get(key)).getValue();
	}
	
	private <T> ConstraintAttributes withT(final String key, final T value) {
		attrs.put(key, new ConstraintAttribute<T>(key, value));
		return this;
	}
}
