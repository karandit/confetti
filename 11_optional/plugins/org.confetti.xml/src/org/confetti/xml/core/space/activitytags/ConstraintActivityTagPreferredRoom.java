package org.confetti.xml.core.space.activitytags;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;
import org.confetti.xml.core.space.SpaceConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"activityTag", "room",
		"active", "comment"})
public class ConstraintActivityTagPreferredRoom extends SpaceConstraint {
	@XmlElement(name = "Activity_Tag") public String activityTag;
	@XmlElement(name = "Room") public String room;
	
	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitSpace(this, param);
	}
}
