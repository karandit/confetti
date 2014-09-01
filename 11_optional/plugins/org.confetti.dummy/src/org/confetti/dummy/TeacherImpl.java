package org.confetti.dummy;

import org.confetti.core.EntityVisitor;
import org.confetti.core.Teacher;

public class TeacherImpl extends EntityImpl implements Teacher{

	public TeacherImpl(String name) {
		super(name);
	}
	
	@Override
	public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
		return visitor.visitTeacher(this, param);
	}
	
}
