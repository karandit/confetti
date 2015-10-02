package org.confetti.fet.dataprovider;

import org.confetti.core.EntityVisitor;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.xml.core.NameGetter;

/**
 * @author Gabor Bubla
 */
public enum RenameVisitor implements EntityVisitor<NameGetter, String> {

	INSTANCE;
	
    @Override public NameGetter visitSubject(Subject subject, String to) 	  { return def().renameSubject(subject, to); }
    @Override public NameGetter visitTeacher(Teacher teacher, String to) 	  { return def().renameTeacher(teacher, to); }
    @Override public NameGetter visitStudentGroup(StudentGroup sg, String to) { return def().renameStudentGroup(sg, to); }
    @Override public NameGetter visitRoom(Room room, String to) 			  { return def().renameRoom(room, to); }

    private NameGetter def() { return new NameGetter(); }

}
