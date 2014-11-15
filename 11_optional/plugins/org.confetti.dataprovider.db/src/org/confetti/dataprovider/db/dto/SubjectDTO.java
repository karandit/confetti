package org.confetti.dataprovider.db.dto;

import org.confetti.core.EntityVisitor;
import org.confetti.core.Subject;

public class SubjectDTO extends EntityDTO implements Subject {
    
    public SubjectDTO(Long id, String name) {
        super(id, name);
    }

    @Override
    public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
        return visitor.visitSubject(this, param);
    }
}