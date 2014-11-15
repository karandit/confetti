package org.confetti.dataprovider.db.entities;

import org.confetti.core.EntityVisitor;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;

/**
 * @author Gabor Bubla
 */
public enum GetEntityClassVisitor implements EntityVisitor<Class<? extends AbstractEntityDb>, Void>{
    INSTANCE;

    @Override public Class<? extends AbstractEntityDb> visitSubject(Subject s, Void p) { return SubjectDb.class; }
    @Override public Class<? extends AbstractEntityDb> visitTeacher(Teacher t, Void p) { return TeacherDb.class; }
    @Override public Class<? extends AbstractEntityDb> visitStudentGroup(StudentGroup s, Void p) { return StudentGroupDb.class; }
    @Override public Class<? extends AbstractEntityDb> visitRoom(Room room, Void param) { return RoomDb.class; }

}
