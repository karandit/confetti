package org.confetti.rcp.constraints;

public interface FieldTypeVisitor2<R, P1, P2> {
	
	R visitBoolean(P1 p1, P2 p2);
	R visitDouble(P1 p1, P2 p2);
	R visitInteger(P1 p1, P2 p2);
	R visitDay(P1 p1, P2 p2);
	R visitHour(P1 p1, P2 p2);
	R visitWeek(P1 p1, P2 p2);
	R visitPeriod(P1 p1, P2 p2);
	R visitInterval(P1 p1, P2 p2);
	R visitTeacher(P1 p1, P2 p2);
	R visitStudentGroup(P1 p1, P2 p2);
	R visitAssignment(P1 p1, P2 p2);
	R visitAssignmentsSet(P1 p1, P2 p2);
	R visitAssignmentsCriteria(P1 p1, P2 p2);
	R visitRoom(P1 p1, P2 p2);
	R visitRoomsSet(P1 p1, P2 p2);
	R visitSubject(P1 p1, P2 p2);

}
