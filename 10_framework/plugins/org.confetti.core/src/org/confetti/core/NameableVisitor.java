package org.confetti.core;

public interface NameableVisitor<R, P> {

	R visitDay(Day day, P param);
	R visitHour(Hour hour, P param);
	R visitSubject(Subject subject, P param);
	R visitTeacher(Teacher teacher, P param);
	R visitStudentGroup(StudentGroup studentGroup, P param);
	R visitRoom(Room room, P param);
	R visitBuilding(Building building, P param);
	R visitTag(Tag tag, P param);

}
