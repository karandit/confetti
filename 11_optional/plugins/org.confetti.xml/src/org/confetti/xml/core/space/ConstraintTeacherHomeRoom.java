package org.confetti.xml.core.space;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"teacher", "room", 
		"active", "comment"})
public class ConstraintTeacherHomeRoom extends SpaceConstraint {
	@XmlElement(name = "Teacher") private String teacher;
	@XmlElement(name = "Room") private String room;
	
	@Override
	protected <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitSpace(this, param);
	}
}
