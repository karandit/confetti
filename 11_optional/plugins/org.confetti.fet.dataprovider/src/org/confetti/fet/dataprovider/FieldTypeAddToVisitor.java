package org.confetti.fet.dataprovider;

import org.confetti.core.Constraint;
import org.confetti.core.ConstraintAttributes;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.rcp.constraints.FieldTypeVisitor;
import org.confetti.util.Triple;

public class FieldTypeAddToVisitor implements FieldTypeVisitor<Object, String, Constraint> {

	private final ConstraintAttributes attrs;

	public FieldTypeAddToVisitor(ConstraintAttributes attrs) {
		this.attrs = attrs;
	}
	
	@Override public Object visitBoolean(String key, Constraint target) { return null; }
	@Override public Object visitDouble(String key, Constraint target) { return null; }
	@Override public Object visitInteger(String key, Constraint target) { return null; }
	@Override public Object visitDay(String key, Constraint target) { return null; }
	@Override public Object visitHour(String key, Constraint target) { return null; }
	@Override public Object visitWeek(String key, Constraint target) { return null; }
	@Override public Object visitPeriod(String key, Constraint target) { return null; }
	@Override public Object visitInterval(String key, Constraint target) { return null; }
	@Override public Object visitTag(String key, Constraint target) { return null; }

	@Override
	public Object visitSubject(String key, Constraint target) {
		((FETSubject) attrs.asSubject(key)).addConstraint(target);
		return null;
	}
	@Override
	public Object visitTeacher(String key, Constraint target) {
		((FETTeacher) attrs.asTeacher(key)).addConstraint(target);
		return null;
	}

	@Override
	public Object visitStudentGroup(String key, Constraint target) {
		((FETStudentGroup) attrs.asStudentGroup(key)).addConstraint(target);
		return null;
	}
	@Override
	public Object visitRoom(String key, Constraint target) {
		((FETRoom) attrs.asRoom(key)).addConstraint(target);
		return null;
	}

	@Override
	public Object visitRoomsSet(String key, Constraint target) {
		attrs.asRoomsSet(key).forEach(room -> ((FETRoom) room).addConstraint(target));
		return null;
	}

	@Override
	public Object visitAssignment(String key, Constraint target) {
		((FETAssignment) attrs.asAssignment(key)).addConstraint(target);
		return null;
	}

	@Override
	public Object visitAssignmentsSet(String key, Constraint target) {
		attrs.asAssignmentsSet(key).forEach(assg -> ((FETAssignment) assg).addConstraint(target));
		return null;
	}
	
	@Override
	public Object visitAssignmentsCriteria(String key, Constraint target) {
		Triple<Subject, Teacher, StudentGroup> crit = attrs.asAssignmentsCriteria(key);
		if (crit.getFirst() != null) {
			((FETSubject) crit.getFirst()).addConstraint(target);
		}
		if (crit.getSecond() != null) {
			((FETTeacher) crit.getSecond()).addConstraint(target);
		}
		if (crit.getThird() != null) {
			((FETStudentGroup) crit.getThird()).addConstraint(target);
		}
		return null;
	}

}
