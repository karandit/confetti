package org.confetti.dataprovider.db.dto;

import org.confetti.core.EntityVisitor;
import org.confetti.core.Teacher;

public class TeacherDTO extends EntityDTO implements Teacher {
    
    public TeacherDTO(String name) {
        super(name);
    }

    @Override
    public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
        return visitor.visitTeacher(this, param);
    }
}