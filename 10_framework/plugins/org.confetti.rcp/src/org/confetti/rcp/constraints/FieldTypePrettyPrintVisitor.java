package org.confetti.rcp.constraints;

import static com.google.common.collect.Iterables.transform;
import static org.confetti.rcp.constraints.FieldTypeCreateControlVisitor.safeGetName;

import org.confetti.core.Assignment;
import org.confetti.core.ConstraintAttributes;
import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.util.Triple;
import org.confetti.util.Tuple;

import com.google.common.collect.Iterables;

public enum FieldTypePrettyPrintVisitor implements FieldTypeVisitor<String, String, ConstraintAttributes> {
	PRETTY_PRINT;

	@Override
	public String visitBoolean(String key, ConstraintAttributes attrs) {
    	return attrs.asBoolean(key).toString();
    }

	@Override
	public String visitDouble(String key, ConstraintAttributes attrs) {
    	return attrs.asDouble(key).toString();
    }

	@Override
	public String visitInteger(String key, ConstraintAttributes attrs) {
    	return attrs.asInteger(key).toString();
    }

	@Override
	public String visitDay(String key, ConstraintAttributes attrs) {
    	return attrs.asInteger(key).toString();
    }

	@Override
	public String visitHour(String key, ConstraintAttributes attrs) {
    	return attrs.asInteger(key).toString();
    }

	@Override
	public String visitWeek(String key, ConstraintAttributes attrs) {
    	return Iterables.toString(transform(attrs.asWeek(key), 
    			tuple -> safeGetName(tuple.getFirst()) + " " + safeGetName(tuple.getSecond()))); //$NON-NLS-1$
    }

	@Override
	public String visitPeriod(String key, ConstraintAttributes attrs) {
    	Tuple<Day, Hour> period = attrs.asPeriod(key);
		return safeGetName(period.getFirst()) + " " + safeGetName(period.getSecond()); //$NON-NLS-1$
    }

	@Override
	public String visitInterval(String key, ConstraintAttributes attrs) {
    	Tuple<Hour, Hour> interval = attrs.asInterval(key);
		return safeGetName(interval.getFirst()) + " " + safeGetName(interval.getSecond()); //$NON-NLS-1$
    }
	
	@Override
	public String visitTeacher(String key, ConstraintAttributes attrs) {
    	return safeGetName(attrs.asTeacher(key));
    }

	@Override
	public String visitStudentGroup(String key, ConstraintAttributes attrs) {
    	return safeGetName(attrs.asStudentGroup(key));
    }

	@Override
	public String visitAssignment(String key, ConstraintAttributes attrs) {
    	return convertAssignmentToString(attrs.asAssignment(key));
    }

	@Override
	public String visitAssignmentsSet(String key, ConstraintAttributes attrs) {
    	return Iterables.toString(transform(attrs.asAssignmentsSet(key), FieldTypePrettyPrintVisitor::convertAssignmentToString));
    }

	@Override
	public String visitAssignmentsCriteria(String key, ConstraintAttributes attrs) {
    	Triple<Subject, Teacher, StudentGroup> triple = attrs.asAssignmentsCriteria(key);
    	return String.format("%s %s %s" //$NON-NLS-1$
        		, safeGetName(triple.getFirst())
        		, safeGetName(triple.getSecond())
        		, safeGetName(triple.getThird())
        		);
    }

	@Override
	public String visitRoom(String key, ConstraintAttributes attrs) {
    	return safeGetName(attrs.asRoom(key));
    }

	@Override
	public String visitRoomsSet(String key, ConstraintAttributes attrs) {
    	return Iterables.toString(transform(attrs.asRoomsSet(key), FieldTypeCreateControlVisitor::safeGetName));
    }

	@Override
	public String visitSubject(String key, ConstraintAttributes attrs) {
    	return safeGetName(attrs.asSubject(key));
    }

	//---------------- helpers -----------------------------------------------------------------------------------------
    private static String convertAssignmentToString(Assignment ass) {
		StringBuilder sb = new StringBuilder()
    	.append("{") //$NON-NLS-1$
    	.append(safeGetName(ass.getSubject()))
    	.append("/") //$NON-NLS-1$
    	.append(Iterables.toString(Iterables.transform(
    			ass.getTeachers().getList(), FieldTypeCreateControlVisitor::safeGetName)))
    	.append("/") //$NON-NLS-1$
    	.append(Iterables.toString(Iterables.transform(
    			ass.getStudentGroups().getList(), FieldTypeCreateControlVisitor::safeGetName)));
    	sb.append("}"); //$NON-NLS-1$
		return sb.toString();
	}
	
}
