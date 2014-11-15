package org.confetti.dataprovider.xml;

import org.confetti.core.EntityVisitor;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.xml.core.InstituteXml;
import org.confetti.xml.core.RoomXml;
import org.confetti.xml.core.SubjectXml;
import org.confetti.xml.core.TeacherXml;
import org.confetti.xml.core.YearXml;

/**
 * @author Gabor Bubla
 */
public class RenameVisitor implements EntityVisitor<Void, String> {

    private final InstituteXml instXml;

    public RenameVisitor(InstituteXml instXml) {
        this.instXml = instXml;
    }

    @Override
    public Void visitSubject(Subject subject, String newName) {
        for (SubjectXml subjectXml : instXml.getSubjects()) {
            if (subjectXml.getName().equals(subject.getName().getValue())) {
                subjectXml.setName(newName);
                return null;
            }
        }
        return null;
    }

    @Override
    public Void visitTeacher(Teacher teacher, String newName) {
        for (TeacherXml teacherXml : instXml.getTeachers()) {
            if (teacherXml.getName().equals(teacher.getName().getValue())) {
                teacherXml.setName(newName);
                return null;
            }
        }
        return null;
    }

    @Override
    public Void visitStudentGroup(StudentGroup studentGroup, String newName) {
        for (YearXml yearXml : instXml.getYears()) {
            if (yearXml.getName().equals(studentGroup.getName().getValue())) {
                yearXml.setName(newName);
                return null;
            }
        }
        return null;
    }

    @Override
    public Void visitRoom(Room room, String newName) {
        for (RoomXml roomXml : instXml.getRooms()) {
            if (roomXml.getName().equals(room.getName().getValue())) {
                roomXml.setName(newName);
                return null;
            }
        }
        return null;
    }   
}
