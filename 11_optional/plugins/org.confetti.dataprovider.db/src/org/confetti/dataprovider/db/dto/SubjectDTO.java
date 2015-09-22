package org.confetti.dataprovider.db.dto;

import org.confetti.core.Subject;

public class SubjectDTO extends EntityDTO implements Subject {
    
    public SubjectDTO(Long id, String name) {
        super(id, name);
    }

	@Override
	public int getColor() {
		return 0;
	}
}