package org.confetti.core;

public interface EntityVisitor<R, P> {

	R visitSubject(Subject subject, P param);
	R visitTeacher(Teacher teacher, P param);
	R visitStudentGroup(StudentGroup studentGroup, P param);
	R visitRoom(Room room, P param);
	
}
