package org.confetti.dataprovider.db.dto;

import org.confetti.core.Teacher;

public class TeacherDTO extends EntityDTO implements Teacher {
    
    public TeacherDTO(Long id, String name) {
        super(id, name);
    }

}