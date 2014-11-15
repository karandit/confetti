package org.confetti.dataprovider.xml;

import static org.confetti.dataprovider.xml.XmlDataProvider.findXmlByName;

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
        SubjectXml found = findXmlByName(instXml.getSubjects(), subject.getName().getValue());
        if (found != null) {
            found.setName(newName);
        }
        return null;
    }

    @Override
    public Void visitTeacher(Teacher teacher, String newName) {
        TeacherXml found = findXmlByName(instXml.getTeachers(), teacher.getName().getValue());
        if (found != null) {
            found.setName(newName);
        }
        return null;
    }

    @Override
    public Void visitStudentGroup(StudentGroup studentGroup, String newName) {
        YearXml found = findXmlByName(instXml.getYears(), studentGroup.getName().getValue());
        if (found != null) {
            found.setName(newName);
        }
        return null;
    }

    @Override
    public Void visitRoom(Room room, String newName) {
        RoomXml found = findXmlByName(instXml.getRooms(), room.getName().getValue());
        if (found != null) {
            found.setName(newName);
        }
        return null;
    }   
}
