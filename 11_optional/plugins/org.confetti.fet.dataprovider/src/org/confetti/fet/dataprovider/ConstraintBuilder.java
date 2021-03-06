package org.confetti.fet.dataprovider;

import org.confetti.core.Assignment;
import org.confetti.core.Constraint;
import org.confetti.core.ConstraintAttributes;
import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Tag;
import org.confetti.core.Teacher;
import org.confetti.fet.xml.core.BaseConstraintXml;
import org.confetti.rcp.extensions.ConstraintDescr;
import org.confetti.rcp.extensions.ConstraintRegistry;
import org.confetti.util.Triple;
import org.confetti.util.Tuple;

public class ConstraintBuilder {

	private final String type;
	private final ConstraintAttributes attrs = new ConstraintAttributes();
	
	public ConstraintBuilder(final String type) {
		this.type = type;
	}
	
	public Constraint build() {
		String fullType = BaseConstraintXml.FET_CONSTRAINTS_NAMESPACE + type;
		FETConstraint constr = new FETConstraint(fullType, attrs);
		
		ConstraintDescr constraintDescr = ConstraintRegistry.INSTANCE.getConstraintDescrById(fullType);
		if (constraintDescr != null) {
			FieldTypeAddToVisitor visitor = new FieldTypeAddToVisitor(attrs);
			constraintDescr.getFields().forEach(field -> field.getType().accept(visitor, field.getName(), constr));
		}
		return constr;
	}
	
	public ConstraintBuilder withInteger(final String key, final Integer v) {
		attrs.withInteger(key, v);
		return this;
	}

	public ConstraintBuilder withDouble(final String key, final Double v) {
		attrs.withDouble(key, v);
		return this;
	}

	public ConstraintBuilder withBoolean(final String key, final Boolean v) {
		attrs.withBoolean(key, v);
		return this;
	}

	public ConstraintBuilder withDay(final String key, final Integer v) {
		attrs.withDay(key, v);
		return this;
	}

	public ConstraintBuilder withHour(final String key, final Integer v) {
		attrs.withHour(key, v);
		return this;
	}

	public ConstraintBuilder withWeek(String key, Iterable<Tuple<Day, Hour>> v) {
		attrs.withWeek(key, v);
		return this;
	}

	public ConstraintBuilder withPeriod(String key, Tuple<Day, Hour> v) {
		attrs.withPeriod(key, v);
		return this;
	}

	public ConstraintBuilder withInterval(String key, Tuple<Hour, Hour> v) {
		attrs.withInterval(key, v);
		return this;
	}

	public ConstraintBuilder withSubject(String key, Subject v) {
		attrs.withSubject(key, v);
		return this;
	}

	public ConstraintBuilder withTeacher(String key, Teacher v) {
		attrs.withTeacher(key, v);
		return this;
	}

	public ConstraintBuilder withStudentGroup(String key, StudentGroup v) {
		attrs.withStudentGroup(key, v);
		return this;
	}

	public ConstraintBuilder withRoom(String key, Room v) {
		attrs.withRoom(key, v);
		return this;
	}

	public ConstraintBuilder withRoomsSet(String key, Iterable<Room> v) {
		attrs.withRoomsSet(key, v);
		return this;
	}

	public ConstraintBuilder withAssignment(String key, Assignment v) {
		attrs.withAssignment(key, v);
		return this;
	}

	public ConstraintBuilder withAssignmentsSet(String key, Iterable<Assignment> v) {
		attrs.withAssignmentsSet(key, v);
		return this;
	}

	public ConstraintBuilder withAssignmentsCriteria(String key, Triple<Subject, Teacher, StudentGroup> v) {
		attrs.withAssignmentsCriteria(key, v);
		return this;
	}

	public ConstraintBuilder withTag(String key, Tag v) {
		attrs.withTag(key, v);
		return this;
	}
	
}
